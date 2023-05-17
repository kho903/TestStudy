package com.jikim.cafekiosk.spring.api.service.product.request;

import com.jikim.cafekiosk.spring.domain.product.Product;
import com.jikim.cafekiosk.spring.domain.product.ProductSellingStatus;
import com.jikim.cafekiosk.spring.domain.product.ProductType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateServiceRequest {

	private ProductType type;

	private ProductSellingStatus sellingStatus;

	private String name;

	private int price;

	@Builder
	public ProductCreateServiceRequest(ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
		this.type = type;
		this.sellingStatus = sellingStatus;
		this.name = name;
		this.price = price;
	}

	public Product toEntity(String productNumber) {
		return Product.builder()
			.productNumber(productNumber)
			.type(type)
			.sellingStatus(sellingStatus)
			.name(name)
			.price(price)
			.build();
	}
}
