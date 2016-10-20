package com.bookshop.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bookshop.valueobjects.Book;


public class BookRowMapper implements RowMapper<Object>{

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		return book;
	}

}
