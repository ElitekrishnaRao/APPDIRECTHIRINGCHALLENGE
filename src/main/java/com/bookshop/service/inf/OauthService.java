package com.bookshop.service.inf;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.rest.services.exceptions.InvalidUrlException;

public interface OauthService {
	
	public abstract void validateRequest(HttpServletRequest request)throws InvalidUrlException;
	
	public abstract InputStream signUrl(String urlValue);
	
}
