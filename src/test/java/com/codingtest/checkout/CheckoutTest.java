package com.codingtest.checkout;

import com.codingtest.exception.CannotFindItemException;
import com.codingtest.pricing.Item;
import com.codingtest.pricing.Special;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CheckoutTest {

	@Test
	public void testFilterItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("A", 10));
		items.add(new Item("B", 20));
		items.add(new Item("C", 30));

		List<Special> specials = new ArrayList<>();
		specials.add(new Special("A", 15, 2));

		List<String> skus = Arrays.asList("A", "B");
		filterItems(items, specials, skus, 2, 1);

		skus = Collections.singletonList("A");
		filterItems(items, specials, skus, 1, 1);

		skus = Arrays.asList("B","C");
		filterItems(items, specials, skus, 2, 0);

		try {
			skus = Collections.singletonList("D");
			new Checkout().filterItems(skus, items);
		} catch (CannotFindItemException e) {
			assertTrue(e.getMessage().equals("Cannot find an item for SKU D"));
		}

	}
	
	@Test
	public void testCalculateTotal() {
		Checkout checkout = new Checkout();
		
		Special special = new Special("A", 30, 3);
		Item item = new Item("A", 10);
		Long total = checkout.calculateTotal(4L, special, item);
		assertTrue(total.equals(40L));
		
		item = new Item("B", 10);
		total = checkout.calculateTotal(3L, null, item);
		assertTrue(total.equals(30L));
	}

	@Test
	public void testCheckout() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("A", 10));
		items.add(new Item("B", 20));
		items.add(new Item("C", 30));
		items.add(new Item("D", 40));

		List<Special> specials = new ArrayList<>();
		specials.add(new Special("A", 15, 2));
		specials.add(new Special("D", 100, 4));

		Checkout checkout = new Checkout();
		checkout.setPricingRules(specials, items);
		Long total = checkout.checkout(Arrays.asList("A", "B", "A", "C", "A", "D"));
		assertTrue(total.equals(115L));

		total = checkout.checkout(Arrays.asList("D", "D", "D","D", "D", "A", "B"));
		assertTrue(total.equals(170L));

		specials.remove(1);
		specials.add(new Special("C", 60, 3));

		checkout.setPricingRules(specials, items);
		total = checkout.checkout(Arrays.asList("D", "D", "C", "D", "A", "B", "D", "C", "A", "C"));
		assertTrue(total.equals(255L));

	}


	private void filterItems(List<Item> items, List<Special> specials, List<String> skus, int itemSize, int specialsSize) {
		Checkout checkout = new Checkout();
		Map<String, Item> filteredItems = checkout.filterItems(skus, items);
		Map<String, Special> filteredSpecials = checkout.filterItems(skus, specials);

		Assert.assertEquals(filteredItems.size(), itemSize);
		Assert.assertEquals(filteredSpecials.size(), specialsSize);

		filteredItems.keySet().forEach(i -> assertTrue(skus.contains(i)));
		filteredSpecials.keySet().forEach(i -> assertTrue(skus.contains(i)));

	}
}
