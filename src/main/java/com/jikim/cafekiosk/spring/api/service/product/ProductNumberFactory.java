package com.jikim.cafekiosk.spring.api.service.product;

import org.springframework.stereotype.Component;

import com.jikim.cafekiosk.spring.domain.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductNumberFactory {

	private final ProductRepository productRepository;

	public String createNextProductNumber() {
		String latestProductNumber = productRepository.findLatestProductNumber();
		if (latestProductNumber == null) {
			return "001";
		}

		int lastedProductNumberInt = Integer.parseInt(latestProductNumber);
		int nextProductNumberInt = lastedProductNumberInt + 1;

		return String.format("%03d", nextProductNumberInt);
	}
}
