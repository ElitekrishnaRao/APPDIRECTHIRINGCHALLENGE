package com.bookshop.service.inf;

import com.bookshop.exceptions.BookNotFoundException;
import com.bookshop.exceptions.DuplicateBookException;
import com.bookshop.valueobjects.Book;

public interface BookServiceDAO {

	public abstract void addBook(Book book) throws DuplicateBookException;
	
	public abstract Book getBook(Book book) throws BookNotFoundException;
	
	public abstract void updateBook(Book book) throws BookNotFoundException;
	
	public abstract void deleteBook(Book book) throws BookNotFoundException;
}
