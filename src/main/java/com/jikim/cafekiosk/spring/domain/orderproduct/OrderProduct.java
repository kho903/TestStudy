package com.jikim.cafekiosk.spring.domain.orderproduct;

import javax.persistence.*;

import com.jikim.cafekiosk.spring.domain.BaseEntity;
import com.jikim.cafekiosk.spring.domain.order.Order;
import com.jikim.cafekiosk.spring.domain.product.Product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	public OrderProduct(Order order, Product product) {
		this.order = order;
		this.product = product;
	}
}
