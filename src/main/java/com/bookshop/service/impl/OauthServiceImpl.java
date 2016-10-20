package com.bookshop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import com.bookshop.constants.SubscriptionConstants;
import com.bookshop.service.inf.OauthService;
import com.rest.services.exceptions.InvalidUrlException;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;

public class OauthServiceImpl implements OauthService{
	

	@Override
	public void validateRequest(HttpServletRequest request) throws InvalidUrlException{
		try {
			OAuthMessage oauthMessage= OAuthServlet.getMessage(request, null);
			OAuthConsumer consumer = new OAuthConsumer(null, SubscriptionConstants.consumerKey, SubscriptionConstants.consumerSecret, null);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			new SimpleOAuthValidator().validateMessage(oauthMessage, accessor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidUrlException(SubscriptionConstants.UNKNOWN_SOURCE);
		}
	}

	@Override
	public InputStream signUrl(String urlValue){
		DefaultOAuthConsumer  consumer = new DefaultOAuthConsumer(SubscriptionConstants.consumerKey, SubscriptionConstants.consumerSecret);
		URL url;
		InputStream is = null;
		try {
			consumer.setSigningStrategy(new QueryStringSigningStrategy());
			String signedUrl= consumer.sign(urlValue);
			url = new URL(signedUrl);
			is = url.openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
		return is;
	}

}
