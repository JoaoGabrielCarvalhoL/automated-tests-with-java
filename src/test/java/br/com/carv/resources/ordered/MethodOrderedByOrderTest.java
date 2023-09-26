package br.com.carv.resources.ordered;

import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class MethodOrderedByOrderTest {

	Logger logger = Logger.getLogger(MethodOrderedByOrderTest.class.getName());
	
	@Test
	void testA() {
		logger.info("First");
	}
	
	@Test
	void testB() {
		logger.info("Second");
	}
	
	@Test
	void testC() {
		logger.info("Fourth");
	}
	
	@Test
	void testD() {
		logger.info("Fifth");
	}
	
	@Test
	void testE() {
		logger.info("Sixth");
	}

}
