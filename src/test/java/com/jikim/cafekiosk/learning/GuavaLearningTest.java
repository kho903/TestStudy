package com.jikim.cafekiosk.learning;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class GuavaLearningTest {

	@DisplayName("주어진 개수만큼 List를 파티셔닝한다. : 3")
	@Test
	void partitioningLearningTest1() throws Exception {
		// given
		List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

		// when
		List<List<Integer>> partition = Lists.partition(integers, 3);

		// then
		assertThat(partition).hasSize(2)
			.isEqualTo(List.of(
				List.of(1, 2, 3), List.of(4, 5, 6)
			));
	}

	@DisplayName("주어진 개수만큼 List를 파티셔닝한다. : 4")
	@Test
	void partitioningLearningTest2() throws Exception {
		// given
		List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

		// when
		List<List<Integer>> partition = Lists.partition(integers, 4);

		// then
		assertThat(partition).hasSize(2)
			.isEqualTo(List.of(
				List.of(1, 2, 3, 4), List.of(5, 6)
			));
	}

	@DisplayName("멀티맵 기능 확인 : 같은 key로 여러 개 put -> Collection 반환")
	@Test
	void multiMapGetTest() throws Exception {
		// given
		Multimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("커피", "아메리카노");
		multimap.put("커피", "카페라떼");
		multimap.put("커피", "카푸치노");
		multimap.put("베이커리", "크루아상");
		multimap.put("베이커리", "식빵");

		// when
		Collection<String> strings = multimap.get("커피");

		// then
		assertThat(strings).hasSize(3)
			.isEqualTo(List.of("아메리카노", "카페라떼", "카푸치노"));
	}

	@DisplayName("멀티맵 remove 기능 확인")
	@TestFactory
	Collection<DynamicTest> multiMapTest() throws Exception {
		// given
		Multimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("커피", "아메리카노");
		multimap.put("커피", "카페라떼");
		multimap.put("커피", "카푸치노");
		multimap.put("베이커리", "크루아상");
		multimap.put("베이커리", "식빵");

		return List.of(
			DynamicTest.dynamicTest("1개 value 삭제", () -> {
				// when
				multimap.remove("커피", "카푸치노");
				Collection<String> result = multimap.get("커피");

				// then
				assertThat(result).hasSize(2)
					.isEqualTo(List.of("아메리카노", "카페라떼"));
			}),
			DynamicTest.dynamicTest("전체 value 삭제", () -> {
				// when
				multimap.removeAll("커피");
				Collection<String> result = multimap.get("커피");

				// then
				assertThat(result).isEmpty();
			})
		);
	}
}
