/**   
* @Title: BookDaoImpl.java 
* @Package cn.itheima.lucene.dao.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 传智 小杨老师 
* @date 2017-10-17 上午9:58:19 
* @version V1.0   
*/
package cn.itheima.lucene.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.itheima.lucene.dao.BookDao;
import cn.itheima.lucene.po.Book;

/** 
 * @ClassName: BookDaoImpl 
 * @Description: 图书dao接口实现 
 * @author 传智 小杨老师  
 * @date 2017-10-17 上午9:58:19 
 *  
 */
public class BookDaoImpl implements BookDao {

	/* (non-Javadoc)
	 * @see cn.itheima.lucene.dao.BookDao#queryBookList()
	 */
	public List<Book> queryBookList() {
		// TODO Auto-generated method stub
		// 创建图书列表List
		List<Book> bookList = new ArrayList<Book>();
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet set = null;
		try{
//			加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			
//			创建数据库连接对象
			con = 
					DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lucene", "root", "admin");
			
//			定义sql语句
			String sql = "select * from book";
			
//			创建satement语句对象
			psmt = con.prepareStatement(sql);
			
//			设置参数
			
//			执行
			set = psmt.executeQuery();
			
//			处理结果集
			while(set.next()){
				// 创建图书对象
				Book book = new Book();
				
//				图书Id
				book.setId(set.getInt("id"));
//				图书名称
				book.setBookname(set.getString("bookname"));
//				图书价格
				book.setPrice(set.getFloat("price"));
//				图书图片
				book.setPic(set.getString("pic"));
				
//				图书描述
				book.setBookdesc(set.getString("bookdesc"));
				
				bookList.add(book);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			释放资源
			try{
				if(set != null) set.close();
				if(psmt != null) psmt.close();
				if(con != null) con.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}

		return bookList;
	}
	
	/**
	 * 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param args    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void main(String [] args){
		// 创建Dao对象
		BookDao bookDao = new BookDaoImpl();
		List<Book> bookList = bookDao.queryBookList();
		for(Book book:bookList){
			System.out.println(book);
		}
	}

}
