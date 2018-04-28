package com.codingtest;

import com.codingtest.checkout.Checkout;
import com.codingtest.pricing.Item;
import com.codingtest.pricing.Special;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Item> items = new ArrayList<>();
		items.add(new Item("A", 50));
		items.add(new Item("B", 30));
		items.add(new Item("C", 20));
		items.add(new Item("D", 15));
		List<Special> specials = new ArrayList<>();
		specials.add(new Special("A", 3, 130));
		specials.add(new Special("B", 2, 45));
		Checkout checkout = new Checkout();
		checkout.setPricingRules(specials, items);
		checkout.checkout(Arrays.asList("A","A","B","A"));
	}
}
