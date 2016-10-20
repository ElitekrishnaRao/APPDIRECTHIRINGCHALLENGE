package com.bookshop.rest.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookshop.service.inf.SubscriptionService;
import com.subscription.notification.beans.ResponseStatus;

@Component
@Path("/subscription")
public class SubscriptionRestController {

	private final static Logger LOGGER = Logger.getLogger(SubscriptionRestController.class.getName());

	@Autowired
	SubscriptionService subscriptionService;

	@GET
	@Path("/create/notification")
	@Produces({"application/json"})
	public Response subscribeCreateNotification(@QueryParam(value = "eventUrl") String eventUrl) {
		LOGGER.log(Level.INFO,"eventUrl");
		ResponseStatus responseStatus = subscriptionService.createNotificationSubscribe(eventUrl);
		return Response.status(200).entity(responseStatus).build();
	}

	@GET
	@Produces({"application/json"})
	@Path("/cancel/notification")
	public Response subscribeChangeNotification(@QueryParam(value = "eventUrl") String eventUrl) {
		ResponseStatus responseStatus = subscriptionService.cancelNotificationSubscribe(eventUrl);
		return Response.status(200).entity(responseStatus).build();
	}

	@GET
	@Produces({"application/json"})
	@Path("/change/notification")
	public Response subscribeCancelNotification(@QueryParam(value = "eventUrl") String eventUrl) {
		ResponseStatus responseStatus = subscriptionService.changeNotificationSubscribe(eventUrl);
		return Response.status(200).entity(responseStatus).build();
	}
	
	@GET
	@Produces({"application/json"})
	@Path("/status/notification")
	public Response subscribeStatusNotification(@QueryParam(value = "eventUrl") String eventUrl) {
		ResponseStatus responseStatus = subscriptionService.statusNotificationSubscribe(eventUrl);
		return Response.status(200).entity(responseStatus).build();
	}
	
	public SubscriptionService getSubscriptionService() {
		return subscriptionService;
	}

	public void setSubscriptionService(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}
}
