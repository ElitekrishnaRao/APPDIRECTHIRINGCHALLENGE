package com.subscription.notification.beans;

public class Item {
	
	private int quantity;

	private String unit;

	public int getQuantity() {
		return quantity;
	}

	public Item setQuantity(int quantity) {
		this.quantity = quantity;
        return this;
	}

	public String getUnit() {
		return unit;
	}

	public Item setUnit(String unit) {
		this.unit = unit;
        return this;
	}
}
