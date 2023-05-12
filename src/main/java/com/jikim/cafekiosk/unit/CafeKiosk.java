package com.jikim.cafekiosk.unit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jikim.cafekiosk.unit.beverage.Beverage;
import com.jikim.cafekiosk.unit.order.Order;

import lombok.Getter;

@Getter
public class CafeKiosk {

	private final List<Beverage> beverages = new ArrayList<>();

	public void add(Beverage beverage) {
		beverages.add(beverage);
	}

	public void remove(Beverage beverage) {
		beverages.remove(beverage);
	}

	public void clear() {
		beverages.clear();
	}

	public int calculateTotalPrice() {
		int totalPrice = 0;
		for (Beverage beverage : beverages) {
			totalPrice += beverage.getPrice();
		}
		return totalPrice;
	}

	public Order createOrder() {
		return new Order(LocalDateTime.now(), beverages);
	}
}
