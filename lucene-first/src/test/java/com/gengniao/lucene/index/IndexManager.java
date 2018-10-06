
package com.gengniao.lucene.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.gengniao.lucene.dao.BookDao;
import com.gengniao.lucene.dao.impl.BookDaoImpl;
import com.gengniao.lucene.po.Book;

/** 
 * @ClassName: IndexManager 
 * @Description: 索引管理类型 
 * @author 传智 小杨老师  
 * @date 2017-10-17 上午10:26:05 
 *  
 */
public class IndexManager {
	
	private static final String INDEX_DIRECTORY="E:\\teach\\0328\\04lucene&solr\\index\\";
	
	// 实现索引流程
	@Test
	public void createIndex() throws IOException{
//		1.采集数据
		BookDao bookDao = new BookDaoImpl();
		List<Book> bookList = bookDao.queryBookList();
		
//		2.创建文档对象（Document）
		List<Document> docList = new ArrayList<Document>();
		for(Book book:bookList){
			// 创建文档对象
			Document doc = new Document();
			
//			图书Id
			// add方法：添加域（Field）
			// TextField：加入文本域
			// 参数一：域的名称
			// 参数二：域值
			// 参数三:指定是否将域值保存到文档中
			doc.add(new TextField("bookId", book.getId()+"", Store.YES));
			
//			图书名称
			doc.add(new TextField("bookName", book.getBookname(), Store.YES));
//			图书价格
			doc.add(new TextField("bookPrice", book.getPrice()+"", Store.YES));
//			图书图片
			doc.add(new TextField("bookPic", book.getPic(), Store.YES));
//			图书描述
			doc.add(new TextField("bookDesc", book.getBookdesc(), Store.YES));
			
			docList.add(doc);
			
		}
		
//		3.创建分析器对象（Analyzer），用于分词
		//Analyzer analyzer = new StandardAnalyzer();
		
		// 使用ik分词器
		Analyzer analyzer = new IKAnalyzer();
		
//		4.创建索引配置对象（IndexWriterConfig），配置lucene
		// 参数一：Lucene版本信息
		// 参数二：分析器对象
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		
//		5.创建索引库目录对象（Directory），用于指定索引库的位置
		File path = new File("E:\\teach\\0328\\04lucene&solr\\index\\");
		Directory directory = FSDirectory.open(path);
		
//		6.创建索引写入对象（IndexWriter），写入索引
		IndexWriter writer = new IndexWriter(directory, iwc);
		
//		7.使用IndexWriter对象，将文档对象（Document）写入索引库
		for(Document doc:docList){
			// addDocument方法：写入索引库
			writer.addDocument(doc);
		}
		
//		8.释放资源
		writer.close();
		
	}
	
	// 读取索引
	@Test
	public void readIndex() throws Exception{
//		1.创建分析器对象（Aanalyzer），用于分词
		//Analyzer analyzer = new StandardAnalyzer();
		
		// 使用ik分词器
		Analyzer analyzer = new IKAnalyzer();
		
//		2.创建查询对象（Query）
		// 创建查询解析器对象
		// 参数一：指定默认搜索的域
		// 参数二：分析器对象
		QueryParser qp = new QueryParser("bookName", analyzer);
		
		// 使用QueryParser对象，实例化Query对象
		// parse方法：实例化Query对象
		// 参数：查询表达式
		// bookName:lucene，表示查询图书名称域中，包含lucene
		Query query = qp.parse("bookName:lucene");
		
//		3.创建索引库目录对象（Directory），指定索引库目录位置
		Directory directory = FSDirectory.open(new File(INDEX_DIRECTORY));
		
//		4.创建索引读取对象（IndexReader），读取索引
		IndexReader reader = DirectoryReader.open(directory);
		
//		5.创建索引搜索对象（IndexSearcher），搜索索引
		IndexSearcher searcher = new IndexSearcher(reader);
		
//		6.使用IndexSearcher对象，执行搜索，返回结果集（TopDocs）
		// search方法：搜索索引
		// 参数一：查询Query对象
		// 参数二：返回排序以后的前n个结果
		TopDocs topDocs = searcher.search(query, 10);
		
//		7.处理结果集
		// 打印实际查询到的结果数量
		System.out.println("实际查询到的结果数量："+topDocs.totalHits);
		
		// scoreDocs:返回文档Id和评分的数组
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for(ScoreDoc sd:scoreDocs){
			// 获取文档ID
			int docId = sd.doc;
			// 获取文档的分值
			float score = sd.score;
			
			System.out.println("--------------------");
			System.out.println("当前文档Id："+docId+",分值："+score);
			
			// 根据文档Id，查询文档内容
			// 相当于关系数据库根据主键查询
			Document doc = searcher.doc(docId);
			
			System.out.println("图书Id："+doc.get("bookId"));
			System.out.println("图书名称："+doc.get("bookName"));
			System.out.println("图书价格："+doc.get("bookPrice"));
			System.out.println("图书图片："+doc.get("bookPic"));
			System.out.println("图书描述："+doc.get("bookDesc"));
			
		}
		
//		8.释放资源
		reader.close();
	}
	
	// 读取索引，扩展分页实现
	@Test
	public void readIndexPage() throws Exception{
//		1.创建分析器对象（Aanalyzer），用于分词
		//Analyzer analyzer = new StandardAnalyzer();
		
		// 使用ik分词器
		Analyzer analyzer = new IKAnalyzer();
		
//		2.创建查询对象（Query）
		// 创建查询解析器对象
		// 参数一：指定默认搜索的域
		// 参数二：分析器对象
		QueryParser qp = new QueryParser("bookName", analyzer);
		
		// 使用QueryParser对象，实例化Query对象
		// parse方法：实例化Query对象
		// 参数：查询表达式
		// bookName:lucene，表示查询图书名称域中，包含lucene
		Query query = qp.parse("bookName:lucene");
		
//		3.创建索引库目录对象（Directory），指定索引库目录位置
		Directory directory = FSDirectory.open(new File(INDEX_DIRECTORY));
		
//		4.创建索引读取对象（IndexReader），读取索引
		IndexReader reader = DirectoryReader.open(directory);
		
//		5.创建索引搜索对象（IndexSearcher），搜索索引
		IndexSearcher searcher = new IndexSearcher(reader);
		
//		6.使用IndexSearcher对象，执行搜索，返回结果集（TopDocs）
		// search方法：搜索索引
		// 参数一：查询Query对象
		// 参数二：返回排序以后的前n个结果
		TopDocs topDocs = searcher.search(query, 10);
		
//		7.处理结果集
		// 打印实际查询到的结果数量
		System.out.println("实际查询到的结果数量："+topDocs.totalHits);
		
		// scoreDocs:返回文档Id和评分的数组
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		// 增加分页实现==================start
		// 定义当前页
		int page=2;// 默认显示第一页
		
		// 定义每一页显示的记录数
		int rows = 2;
		
		// 计算记录起始数
		int start = (page-1)*rows;
		
		// 计算记录结束数
		// 记录起始数+每一页显示的记录数
		// 实际查询的结果数量
		// 取最小值，防止数组越界
		int end = Math.min(start+rows, scoreDocs.length);

		// 增加分页实现==================end
		// for(ScoreDoc sd:scoreDocs){
		
		for(int i=start;i<end;i++){
			// 获取文档ID
			int docId = scoreDocs[i].doc;
			// 获取文档的分值
			float score = scoreDocs[i].score;
			
			System.out.println("--------------------");
			System.out.println("当前文档Id："+docId+",分值："+score);
			
			// 根据文档Id，查询文档内容
			// 相当于关系数据库根据主键查询
			Document doc = searcher.doc(docId);
			
			System.out.println("图书Id："+doc.get("bookId"));
			System.out.println("图书名称："+doc.get("bookName"));
			System.out.println("图书价格："+doc.get("bookPrice"));
			System.out.println("图书图片："+doc.get("bookPic"));
			System.out.println("图书描述："+doc.get("bookDesc"));
			
		}
		
//		8.释放资源
		reader.close();
	}
	

}
