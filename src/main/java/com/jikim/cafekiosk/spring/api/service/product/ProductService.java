package com.jikim.cafekiosk.spring.api.service.product;

import static com.jikim.cafekiosk.spring.domain.product.ProductSellingStatus.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikim.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.jikim.cafekiosk.spring.api.service.product.request.ProductCreateServiceRequest;
import com.jikim.cafekiosk.spring.api.service.product.response.ProductResponse;
import com.jikim.cafekiosk.spring.domain.product.Product;
import com.jikim.cafekiosk.spring.domain.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	@Transactional
	public ProductResponse createProduct(ProductCreateServiceRequest request) {
		// productNumber
		// 001 002 003 004
		// DB 에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
		// 009 -> 010
		// nextProductNumber

		String nextProductNumber = createNextProductNumber();

		// Product
		Product product = request.toEntity(nextProductNumber);
		Product savedProduct = productRepository.save(product);

		return ProductResponse.of(savedProduct);
	}

	private String createNextProductNumber() {
		String latestProductNumber = productRepository.findLatestProductNumber();
		if (latestProductNumber == null) {
			return "001";
		}

		int lastedProductNumberInt = Integer.parseInt(latestProductNumber);
		int nextProductNumberInt = lastedProductNumberInt + 1;

		return String.format("%03d", nextProductNumberInt);
	}

	public List<ProductResponse> getSellingProducts() {
		List<Product> products = productRepository.findAllBySellingStatusIn(forDisplay());
		return products.stream()
			.map(ProductResponse::of)
			.collect(Collectors.toList());
	}
}
