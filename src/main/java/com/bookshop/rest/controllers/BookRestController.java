package com.bookshop.rest.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookshop.constants.BookConstants;
import com.bookshop.exceptions.BookNotFoundException;
import com.bookshop.exceptions.DuplicateBookException;
import com.bookshop.service.inf.BookServiceDAO;
import com.bookshop.utility.ErrorHandler;
import com.bookshop.valueobjects.Book;
import com.subscription.notification.beans.ResponseStatus;

@Component
@Path("/BookService")
public class BookRestController {
	
	private final static Logger LOGGER = Logger.getLogger(BookRestController.class.getName());

	@Autowired
	BookServiceDAO bookServiceDAO;

	@GET
	@Produces({"application/json"})
	@Path("/addBook/bookName/{bookTitle}/bookAuthor/{bookAuthor}")
	public Response addBook(@PathParam("bookTitle") String bookTitle,@PathParam("bookAuthor") String bookAuthor) {
		Book book = new Book(bookTitle,bookAuthor);
		LOGGER.log(Level.INFO,book.getAuthor());
		LOGGER.log(Level.INFO,book.getTitle());
		try {
			bookServiceDAO.addBook(book);
		} catch (DuplicateBookException e) {
			e.printStackTrace();
			ResponseStatus rs = ErrorHandler.getFailureResponse(BookConstants.BookAlreadyExists, e.getMessage());
			return Response.status(200).entity(rs).build();
		}
		return Response.status(200).entity(book).build();

	}

	@GET
	@Produces({"application/json"})
	@Path("/updateBook/bookName/{bookTitle}/bookAuthor/{bookAuthor}")
	public Response updateBook(@PathParam("bookTitle") String bookTitle,@PathParam("bookAuthor") String bookAuthor) {
		Book book = new Book(bookTitle,bookAuthor);
		LOGGER.log(Level.INFO,book.getAuthor());
		LOGGER.log(Level.INFO,book.getTitle());
		try {
			bookServiceDAO.updateBook(book);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
			ResponseStatus rs = ErrorHandler.getFailureResponse(BookConstants.BookNotFound, e.getMessage());
			return Response.status(200).entity(rs).build();
		}
		return Response.status(200).entity(BookConstants.UPDAATION_MESSAGE).build();

	}

	@GET
	@Produces({"application/json"})
	@Path("/getBook/bookName/{bookTitle}/")
	public Response getBook(@PathParam("bookTitle") String bookTitle) {
		Book book = new Book();
		book.setTitle(bookTitle);
		try {
			book  = bookServiceDAO.getBook(book);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
			ResponseStatus rs = ErrorHandler.getFailureResponse(BookConstants.BookNotFound, e.getMessage());
			return Response.status(200).entity(rs).build();
		}
		return Response.status(200).entity(book).build();

	}

	@GET
	@Produces({"application/json"})
	@Path("/deleteBook/bookName/{bookTitle}")
	public Response deleteBook(@PathParam("bookTitle") String bookTitle) {
		Book book = new Book();
		book.setTitle(bookTitle);
		try {
			bookServiceDAO.deleteBook(book);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
			ResponseStatus rs = ErrorHandler.getFailureResponse(BookConstants.BookNotFound, e.getMessage());
			return Response.status(200).entity(rs).build();
		}
		return Response.status(200).entity(BookConstants.DELETION_MESSAGE).build();

	}
}
