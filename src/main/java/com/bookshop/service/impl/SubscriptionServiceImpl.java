package com.bookshop.service.impl;

import java.io.InputStream;
import java.util.UUID;

import com.bookshop.constants.SubscriptionConstants;
import com.bookshop.service.inf.SubscriptionService;
import com.bookshop.utility.ErrorHandler;
import com.subscription.json.JsonReaderWriter;
import com.subscription.notification.beans.Event;
import com.subscription.notification.beans.ResponseStatus;

public class SubscriptionServiceImpl extends OauthServiceImpl implements SubscriptionService {

	@Override
	public ResponseStatus createNotificationSubscribe(String url) {
		//Retrieve data from app direct and parse it
		InputStream inputStream = signUrl(url);
		JsonReaderWriter jsonReader = new JsonReaderWriter();
		Event event = jsonReader.parseJson(inputStream);
		//Construct response
		if(event!=null && event.getMarketplace()!=null){
			/*Handle code to create a user and send the uuid as further identifier
			 * Here lookup in db can be done whether identifier is present in db if 
			 * not create and send it as given below
			 */
			System.out.println("Event proccessed :-" +event.getMarketplace().getBaseUrl());
			if(event.getPayload().getAccount()!=null){
			String uuid= event.getPayload().getAccount().getAccountIdentifier();
			}else{
				//create uuid by picking random id and send it back
			}
			return  ErrorHandler.getSuccessResponse(SubscriptionConstants.BLANK_STRING, UUID.randomUUID().toString());
		}else{
			return ErrorHandler.getFailureResponse(SubscriptionConstants.ERROR_URL_FAIL,SubscriptionConstants.ERROR_APPDIRECT);
		}
	}

	@Override
	public ResponseStatus changeNotificationSubscribe(String url) {
		InputStream inputStream = signUrl(url);
		JsonReaderWriter jsonReader = new JsonReaderWriter();
		Event event = jsonReader.parseJson(inputStream);
		if(event!=null){
			//retrieve accountidentifier and change order details in DB in db 
			String uuid= event.getPayload().getAccount().getAccountIdentifier();
			event.getPayload().getOrder();
			return  ErrorHandler.getSuccessResponse(SubscriptionConstants.BLANK_STRING, UUID.randomUUID().toString());
		}else{
			return ErrorHandler.getFailureResponse(SubscriptionConstants.ERROR_URL_FAIL,SubscriptionConstants.ERROR_APPDIRECT);
		}
	}

	@Override
	public ResponseStatus cancelNotificationSubscribe(String url) {
		InputStream inputStream = signUrl(url);
		JsonReaderWriter jsonReader = new JsonReaderWriter();
		Event event = jsonReader.parseJson(inputStream);
		if(event!=null){
			//retrieve accountidentifier and remove it from Db
			String uuid= event.getPayload().getAccount().getAccountIdentifier();
			return  ErrorHandler.getSuccessResponse("", UUID.randomUUID().toString());
		}else{
			return ErrorHandler.getFailureResponse(SubscriptionConstants.ERROR_URL_FAIL,SubscriptionConstants.ERROR_APPDIRECT);
		}

	}

	@Override
	public ResponseStatus statusNotificationSubscribe(String url) {
		InputStream inputStream = signUrl(url);
		JsonReaderWriter jsonReader = new JsonReaderWriter();
		Event event = jsonReader.parseJson(inputStream);
		if(event!=null){
			//retrieve account-identifier and change status in db 
			String message = event.getPayload().getNotice().getType();
			if(message.equals(SubscriptionConstants.CLOSED)){
				//perform delete operation using uuid 
			}else if(message.equals(SubscriptionConstants.DEACTIVATED)){
				//perform delete operation using uuid and set status suspended
			}else if(message.equals(SubscriptionConstants.REACTIVATED)){
				//perform delete operation using uuid and set status active
			}else if(message.equals(SubscriptionConstants.UPCOMING_INVOICE)){
				//perform delete operation using uuid and set status active
			}
			return  ErrorHandler.getSuccessResponse(SubscriptionConstants.BLANK_STRING, UUID.randomUUID().toString());
		}else{
			return ErrorHandler.getFailureResponse(SubscriptionConstants.ERROR_URL_FAIL,SubscriptionConstants.ERROR_APPDIRECT);
		}

	}

}
