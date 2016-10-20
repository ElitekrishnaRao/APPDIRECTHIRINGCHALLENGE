package com.bookshop.service.inf;

import com.subscription.notification.beans.ResponseStatus;

public interface SubscriptionService {

	public abstract ResponseStatus createNotificationSubscribe(String url);
	
	public abstract ResponseStatus changeNotificationSubscribe(String url);
	
	public abstract ResponseStatus cancelNotificationSubscribe(String url);
	
	public abstract ResponseStatus statusNotificationSubscribe(String url);
}
