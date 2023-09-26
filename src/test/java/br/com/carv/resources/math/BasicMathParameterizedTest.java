package br.com.carv.resources.math;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BasicMathParameterizedTest {

	@ParameterizedTest
	@MethodSource("test_div_input")
	@DisplayName("Test with method arguments")
	void test_div(BigDecimal dividend, BigDecimal divider, BigDecimal expected) {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.div(dividend, divider);
		Assertions.assertEquals(expected, result);
	}
	
	public static Stream<Arguments> test_div_input() {
		return Stream.of(
				Arguments.of(new BigDecimal(10), new BigDecimal(5), new BigDecimal(2)),
				Arguments.of(new BigDecimal(15), new BigDecimal(5), new BigDecimal(3)), 
				Arguments.of(new BigDecimal(5), new BigDecimal(5), new BigDecimal(1)));
				
	}
	
	@ParameterizedTest
	@CsvSource({"20, 10, 2", "7, 7, 1", "48, 8, 6"})
	@DisplayName("Test with CSV Source")
	void test_div_csv_source(BigDecimal dividend, BigDecimal divider, BigDecimal expected) {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.div(dividend, divider);
		Assertions.assertEquals(expected, result);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/div.csv")
	@DisplayName("Test with CSV File")
	void test_div_csv_file(BigDecimal dividend, BigDecimal divider, BigDecimal expected) {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.div(dividend, divider);
		Assertions.assertEquals(expected, result);
	}

}
