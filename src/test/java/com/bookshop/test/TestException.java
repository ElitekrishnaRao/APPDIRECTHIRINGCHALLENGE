package com.bookshop.test;

import com.rest.services.exceptions.InvalidUrlException;

public class TestException {

	public static void main(String[] args) {
      try{
    	  throw new InvalidUrlException("Invalid Url");
      }catch(InvalidUrlException e){
    	  e.getMessage();
    	  System.out.println(e.getMessage());
      }
	}

}
