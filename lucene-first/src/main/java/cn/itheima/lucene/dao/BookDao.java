/**   
* @Title: BookDao.java 
* @Package cn.itheima.lucene.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 传智 小杨老师 
* @date 2017-10-17 上午9:56:54 
* @version V1.0   
*/
package cn.itheima.lucene.dao;

import java.util.List;

import cn.itheima.lucene.po.Book;

/** 
 * @ClassName: BookDao 
 * @Description: 图书dao接口 
 * @author 传智 小杨老师  
 * @date 2017-10-17 上午9:56:54 
 *  
 */
public interface BookDao {
	
	// 查询全部图书列表
	List<Book> queryBookList();

}
