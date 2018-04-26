package com.codingtest.pricing;

public class Special {

	private String sku;

	private int quantity;

	private int price;

	public Special(String sku, int quantity, int price) {
		this.sku = sku;
		this.quantity = quantity;
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
