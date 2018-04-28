package com.codingtest.pricing;

public class Special extends Item {

	private int quantity;

	public Special(String sku, int price, int quantity) {
		super(sku, price);
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}
}
