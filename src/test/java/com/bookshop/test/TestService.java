package com.bookshop.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscription.notification.beans.Event;

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

public class TestService {

	public void validate(HttpServletRequest request){
		try {
			OAuthMessage oauthMessage= OAuthServlet.getMessage(request, null);
			OAuthConsumer consumer = new OAuthConsumer(null, "appdirecthiringchallenge-139400", "PwWDSVq6kOaL", null);
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			new SimpleOAuthValidator().validateMessage(oauthMessage, accessor);
			System.out.println("Valiation completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InputStream signUrl(String urlValue){
		DefaultOAuthConsumer  consumer = new DefaultOAuthConsumer("appdirecthiringchallenge-139400", "PwWDSVq6kOaL");
		URL url;
		InputStream is = null;
		try {
			consumer.setSigningStrategy(new QueryStringSigningStrategy());
			String signedUrl= consumer.sign(urlValue);
			url = new URL(signedUrl);
			is = url.openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

	public static void main(String args[]){
		TestService baseService= new TestService();
		baseService.signUrl("https://marketplace.appdirect.com/api/integration/v1/events/87cf1e87-9e22-468d-a7cd-0f0b2ed8fa43");
	}
}

