package br.com.apirestfull.apigateway.integrationtest.swagger;

import br.com.apirestfull.apigateway.configs.TesteConfigs;
import br.com.apirestfull.apigateway.integrations.testcontainers.AbstractIntegrationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTests  extends AbstractIntegrationTest {

    @Test
    void shouldDisplaySwaggerUIPage(){
        var content = given().basePath("/swagger-ui/index.html")
                .port(TesteConfigs.SERVER_PORT)
                .when().get().then().statusCode(200)
                .extract().body().asString();
        Assertions.assertTrue(content.contains("Swagger UI"));
    }
}
