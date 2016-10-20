package com.subscription.notification.beans;

import java.util.List;

public class Order {
	
	private String editionCode;

	private List<Item> items;

	private String pricingDuration;


	public String getEditionCode() {
		return editionCode;
	}


	public Order setEditionCode(String editionCode) {
		this.editionCode = editionCode;
        return this;
	}


	public List<Item> getItems() {
		return items;
	}


	public Order setItems(List<Item> items) {
		this.items = items;
        return this;
	}


	public String getPricingDuration() {
		return pricingDuration;
	}


	public Order setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
        return this;
	}

}
