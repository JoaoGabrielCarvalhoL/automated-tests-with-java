package br.com.carv.resources.math;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

class BasicMathRepeatedTest {

	@RepeatedTest(3)
	@DisplayName("Repeated Test")
	void testDiv_When_Divided_by_Zero(RepetitionInfo repInfo, TestInfo info) {
		BasicMath basicMath = new BasicMath();
		System.out.println("Repetition N°: " + repInfo.getCurrentRepetition() + " of " + repInfo.getTotalRepetitions());
		System.out.println("Running " + info.getTestMethod().get().getName());
		Assertions.assertThrows(ArithmeticException.class, () -> {
			basicMath.div(new BigDecimal(1), BigDecimal.ZERO);
		}); 
	}

}
