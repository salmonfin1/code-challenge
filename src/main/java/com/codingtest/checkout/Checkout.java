package com.codingtest.checkout;

import com.codingtest.pricing.Item;
import com.codingtest.pricing.Special;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class Checkout {

	private List<Special> specials;
	private List<Item> items;


	public void setPricingRules(List<Special> specials, List<Item> items) {
		this.specials = specials;
		this.items = items;
	}

	public int checkout(List<String> skus) {
		Map<String, Special> specialMap = filterSpecials(skus);

		Map<String, Long> itemCount = skus.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		int total;
		for (Map.Entry<String, Long> itemEntry : itemCount.entrySet()) {
			if (nonNull(specialMap.get(itemEntry.getKey()))) {

			}
		}

	}

	private Map<String, Special> filterSpecials(List<String> skus) {
		return this.specials.stream()
				.filter(s -> skus.contains(s.getSku()))
				.collect(Collectors.toMap(Special::getSku, Function.identity()));
	}


}
