package com.subscription.json;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscription.notification.beans.Event;

public class JsonReaderWriter {
   
	public Event parseJson(InputStream inputStream){
		Event event = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			//JSON from file to Object
			event = mapper.readValue(inputStream, Event.class);
			event.getMarketplace().getBaseUrl();
			System.out.println(event.getMarketplace().getBaseUrl());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}
	
	public String convertObjectToJson(Object object){
		String json = "";
		//json.get
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
