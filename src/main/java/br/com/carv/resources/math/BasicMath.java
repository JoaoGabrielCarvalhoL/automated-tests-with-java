package br.com.carv.resources.math;

import java.math.BigDecimal;
import java.math.MathContext;

public class BasicMath {

	public BigDecimal sum(BigDecimal firstValue, BigDecimal secondValue) {
		 return firstValue.add(secondValue);
	}
	
	public BigDecimal sub(BigDecimal firstValue, BigDecimal secondValue) {
		return firstValue.subtract(secondValue);
	}
	
	public BigDecimal mult(BigDecimal firstValue, BigDecimal secondValue) {
		return firstValue.multiply(secondValue);
	}
	
	public BigDecimal div(BigDecimal firstValue, BigDecimal secondValue) {
		return firstValue.divide(secondValue);
	}
	
	public BigDecimal squareRoot(BigDecimal value) {
		return value.sqrt(MathContext.DECIMAL32);
	}
	
}
