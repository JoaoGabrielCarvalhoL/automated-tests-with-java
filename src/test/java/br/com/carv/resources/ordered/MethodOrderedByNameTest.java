package br.com.carv.resources.ordered;

import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderedByNameTest {

	Logger logger = Logger.getLogger(MethodOrderedByNameTest.class.getName());
	
	@Test
	@Order(1)
	void testA() {
		logger.info("First");
	}
	
	@Test
	@Order(4)
	void testB() {
		logger.info("Second");
	}
	
	@Test
	@Order(2)
	void testC() {
		logger.info("Fourth");
	}
	
	@Test
	@Order(5)
	void testD() {
		logger.info("Fifth");
	}
	
	@Test
	@Order(3)
	void testE() {
		logger.info("Sixth");
	}

}
