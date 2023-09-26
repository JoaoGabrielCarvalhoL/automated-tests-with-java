package br.com.carv.resources.math;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@DisplayName("")
class BasicMathTest {

	//Method nomenclature: test[System Under Test]_[Condition or State Change]_[Expected Result]
	
	@BeforeAll
	static void setup() {
		System.out.println("Running @BeforeAll method!");
	}
	
	@AfterAll
	static void cleanUp() {
		System.out.println("Running @AfterAll method!");
	}
	
	@Test
	@DisplayName("")
	void testSum_When_Ten_Add_Fifteen_Should_Return_Twenty_Five() {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.sum(new BigDecimal(10), new BigDecimal(15));
		Assertions.assertEquals(BigDecimal.valueOf(25), result, 
				() -> "The test_sum did not produce expected result");
	}
	
	@Test
	@DisplayName("")
	void test_sub() {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.sub(new BigDecimal(10), new BigDecimal(5));
		Assertions.assertEquals(BigDecimal.valueOf(5), result, 
				() -> "The test_sub did not produce expected result");
	}
	
	@Test
	@DisplayName("")
	void test_mult() {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.mult(new BigDecimal(10), new BigDecimal(5));
		Assertions.assertEquals(BigDecimal.valueOf(50), result, 
				() -> "The test_mult did not produce expected result");
	}
	
	@Test
	@DisplayName("")
	void test_div() {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.div(new BigDecimal(10), new BigDecimal(5));
		Assertions.assertEquals(BigDecimal.valueOf(2), result, 
				() -> "The test_div did not produce expected result");
	}
	
	@Test
	@DisplayName("")
	void testDiv_When_Divided_by_Zero() {
		BasicMath basicMath = new BasicMath();
		Assertions.assertThrows(ArithmeticException.class, () -> {
			basicMath.div(new BigDecimal(1), BigDecimal.ZERO);
		}); 
	}
	
	@Test
	@DisplayName("")
	void test_square_root() {
		BasicMath basicMath = new BasicMath();
		BigDecimal result = basicMath.squareRoot(new BigDecimal(81));
		Assertions.assertEquals(BigDecimal.valueOf(9), result, 
				() -> "The test_square_root did not produce expected result");
	}
	
	@Test
	@Timeout(value = 1, unit = TimeUnit.MILLISECONDS)
	void test_performance() {
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
		}
		
	}

}
