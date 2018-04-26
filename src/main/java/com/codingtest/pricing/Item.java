package com.codingtest.pricing;

public class Item {

	private String sku;

	private int unitPrice;

	public Item(String sku, int unitPrice) {
		this.sku = sku;
		this.unitPrice = unitPrice;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
}
