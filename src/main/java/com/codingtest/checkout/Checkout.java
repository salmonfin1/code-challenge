package com.codingtest.checkout;

import com.codingtest.exception.CannotFindItemException;
import com.codingtest.pricing.Item;
import com.codingtest.pricing.Special;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Checkout {

	private List<Special> specials;
	private List<Item> items;


	public void setPricingRules(List<Special> specials, List<Item> items) {
		this.specials = specials;
		this.items = items;
	}

	public Long checkout(List<String> skus) {
		Map<String, Special> specialMap = filterItems(skus, this.specials);
		Map<String, Item> itemMap = filterItems(skus, this.items);

		Map<String, Long> itemCount = skus.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Long total = 0L;

		for (Map.Entry<String, Long> itemEntry : itemCount.entrySet()) {
			String sku = itemEntry.getKey();
			Long count = itemEntry.getValue();
			Special special = specialMap.get(sku);
			Item item = itemMap.get(sku);

			if (isNull(item)) {
				throw new CannotFindItemException("Cannot find an item for SKU " + sku);
			}

			total += calculateTotal(count, special, item);
		}
		return total;
	}

	Long calculateTotal(Long count, Special special, Item item) {
		Long total = 0L;
		if (nonNull(special) && special.getQuantity() <= count) {
			total += special.getPrice();
			count -= special.getQuantity();
			if (count > 0) {
				total += count * item.getPrice();
			}
		} else {
			total += count * item.getPrice();
		}

		return total;
	}

	<U extends Item> Map<String, U> filterItems(List<String> skus, List<U> items) {
		return items.stream()
				.filter(s -> skus.contains(s.getSku()))
				.collect(Collectors.toMap(U::getSku, Function.identity()));
	}


}
