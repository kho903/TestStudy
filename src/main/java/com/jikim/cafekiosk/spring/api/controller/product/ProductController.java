package com.jikim.cafekiosk.spring.api.controller.product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jikim.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.jikim.cafekiosk.spring.api.service.product.ProductResponse;
import com.jikim.cafekiosk.spring.api.service.product.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/api/v1/products/new")
	public void createProduct(ProductCreateRequest request) {
		productService.createProduct(request);
	}

	@GetMapping("/api/v1/products/selling")
	public List<ProductResponse> getSellingProducts() {
		return productService.getSellingProducts();
	}
}
