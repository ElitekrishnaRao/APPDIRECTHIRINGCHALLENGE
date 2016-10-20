package com.subscription.filters;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import com.bookshop.constants.SubscriptionConstants;
import com.bookshop.service.impl.OauthServiceImpl;
import com.bookshop.service.inf.OauthService;
import com.bookshop.utility.ErrorHandler;
import com.rest.services.exceptions.InvalidUrlException;
import com.subscription.notification.beans.ResponseStatus;

@PreMatching
@Provider
public class ValidateRequest implements ContainerRequestFilter {
	private static final Logger logger = Logger.getLogger(ValidateRequest.class.getName());

	@Context
	private HttpServletRequest servletRequest;

	@Override
	public void  filter(ContainerRequestContext requestContext) {
		System.out.println("Entering filter");
		OauthService oauthService = new OauthServiceImpl();
		if(servletRequest.getRequestURI().contains("subscription")){
			try {
				oauthService.validateRequest(servletRequest);
			} catch (InvalidUrlException ex) {
				ex.printStackTrace();
				ResponseStatus responseStatus = ErrorHandler.getFailureResponse(ex.getMessage(),SubscriptionConstants.INVALID_TOKEN);
				//String json = jsonReaderWriter.convertObjectToJson(responseStatus);
				ResponseBuilder builder = null;
				builder = Response.status(Response.Status.UNAUTHORIZED).entity(responseStatus);
				throw new WebApplicationException(builder.build());
			}
		}else{
			System.out.println("Falls under book service");
		}
	}

	public static Logger getLogger() {
		return logger;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

}
