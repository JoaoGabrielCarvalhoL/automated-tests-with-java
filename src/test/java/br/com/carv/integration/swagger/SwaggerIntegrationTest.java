package br.com.carv.integration.swagger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.carv.config.TestConfig;
import br.com.carv.integration.testcontainers.AbstractIntegrationTest;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest{

	@DisplayName("Should Display Swagger UI Page")
	@Test
	void testShouldDisplaySwaggerUI() {
		String content = RestAssured.given().basePath("/swagger-ui/index.html").port(TestConfig.SERVER_PORT).when()
				.get().then().statusCode(200).extract().body().asString();
		
		Assertions.assertTrue(content.contains("Swagger UI"));
	}

}
