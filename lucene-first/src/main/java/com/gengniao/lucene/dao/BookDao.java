
package com.gengniao.lucene.dao;

import java.util.List;

import com.gengniao.lucene.po.Book;

public interface BookDao {
	
	// 查询全部图书列表
	List<Book> queryBookList();

}
