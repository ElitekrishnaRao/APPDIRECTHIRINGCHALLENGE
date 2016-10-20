package com.bookshop.constants;

public class QueryConstants {
	public static String INSERT_BOOK = "insert into Book values(?,?)";
    public static String GET_BOOK = "select *from Book where title=?";
    public static String UPDATE_BOOK = "update Book set title=?,author=? where title=? or author=?" ;
    public static String DELETE_BOOK = "delete from Book where title=?";
}
