package com.bookshop.utility;

import com.subscription.notification.beans.ResponseStatus;

public class ErrorHandler {

	public static ResponseStatus getFailureResponse(String errCode,String errorMessage) {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setSuccess("false");
		responseStatus.setErrcode(errCode);
		responseStatus.setMessage(errorMessage);
		return responseStatus;
	}

	public static ResponseStatus getSuccessResponse(String message,String identifier) {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setSuccess("true");
		responseStatus.setAccountIdentifier(identifier);
		responseStatus.setMessage("Account created and user id is 10308092");
		return responseStatus;
	}
}
