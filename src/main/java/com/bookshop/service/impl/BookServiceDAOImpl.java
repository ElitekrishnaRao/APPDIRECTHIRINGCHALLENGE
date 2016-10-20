package com.bookshop.service.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bookshop.constants.BookConstants;
import com.bookshop.constants.QueryConstants;
import com.bookshop.exceptions.BookNotFoundException;
import com.bookshop.exceptions.DuplicateBookException;
import com.bookshop.service.inf.BookServiceDAO;
import com.bookshop.utility.BookRowMapper;
import com.bookshop.valueobjects.Book;

@Component
public class BookServiceDAOImpl implements BookServiceDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addBook(Book book) throws DuplicateBookException {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// define query arguments
		Object[] params = new Object[] { book.getTitle(),book.getAuthor()};
		// define object types arguments
		int[] types = {Types.VARCHAR, Types.VARCHAR};
		try {
			if(getBook(book)!=null){
				throw new DuplicateBookException(BookConstants.DUPLICATE_BOOK);
			}
		} catch (BookNotFoundException e) {
			jdbcTemplate.update(QueryConstants.INSERT_BOOK, params, types);
		}
	}

	@Override
	public Book getBook(Book book) throws BookNotFoundException {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// define query arguments
		Object[] params = new Object[] { book.getTitle()};
		// define object types arguments
		int[] types = {Types.VARCHAR};
		try{
			book = (Book)jdbcTemplate.queryForObject(
					QueryConstants.GET_BOOK,params,types,new BookRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new BookNotFoundException(BookConstants.BOOK_NOTFOUND);
		}
		return book;
	}

	@Override
	public void updateBook(Book book) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// define query arguments
		Object[] params = new Object[] { book.getTitle(),book.getAuthor(),book.getTitle(),book.getAuthor()};
		// define object types arguments
		int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR};
		jdbcTemplate.update(QueryConstants.UPDATE_BOOK, params, types);

	}

	@Override
	public void deleteBook(Book book) throws BookNotFoundException {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// define query arguments
		Object[] params = new Object[] { book.getTitle()};
		// define object types arguments
		int[] types = {Types.VARCHAR};
		int rowsUpdated =  jdbcTemplate.update(QueryConstants.DELETE_BOOK, params, types);
		if(rowsUpdated<=0){
			throw new BookNotFoundException(BookConstants.BOOK_NOTFOUND);
		}

	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
