package com.jikim.cafekiosk.spring.api.service.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jikim.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import com.jikim.cafekiosk.spring.api.service.order.response.OrderResponse;
import com.jikim.cafekiosk.spring.domain.order.Order;
import com.jikim.cafekiosk.spring.domain.order.OrderRepository;
import com.jikim.cafekiosk.spring.domain.product.Product;
import com.jikim.cafekiosk.spring.domain.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;

	public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
		List<String> productNumbers = request.getProductNumbers();
		// Product
		List<Product> products = findProductsBy(productNumbers);

		// Order
		Order order = Order.create(products, registeredDateTime);
		Order savedOrder = orderRepository.save(order);

		return OrderResponse.of(savedOrder);
	}

	private List<Product> findProductsBy(List<String> productNumbers) {
		List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
		Map<String, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getProductNumber, p -> p));

		return productNumbers.stream()
			.map(productMap::get)
			.collect(Collectors.toList());
	}
}
