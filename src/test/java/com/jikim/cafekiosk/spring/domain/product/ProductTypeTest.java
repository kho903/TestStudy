package com.jikim.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTypeTest {

	@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
	@Test
	void containsStockTypeFalse() throws Exception {
		// given
		ProductType givenType = ProductType.HANDMADE;

		// when
		boolean result = ProductType.containsStockType(givenType);

		// then
		assertThat(result).isFalse();
	}

	@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
	@Test
	void containsStockTypeTrue() throws Exception {
		// given
		ProductType givenType = ProductType.BAKERY;

		// when
		boolean result = ProductType.containsStockType(givenType);

		// then
		assertThat(result).isTrue();
	}
}