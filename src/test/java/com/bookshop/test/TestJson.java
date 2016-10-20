package com.bookshop.test;

import com.bookshop.utility.ErrorHandler;
import com.subscription.json.JsonReaderWriter;
import com.subscription.notification.beans.ResponseStatus;

public class TestJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonReaderWriter jsonReaderWriter = new JsonReaderWriter();
		ResponseStatus responseStatus = ErrorHandler.getFailureResponse("FailedCase","error");
		String json = jsonReaderWriter.convertObjectToJson(responseStatus);
		System.out.println("Json :-"+ json);
	}

}
