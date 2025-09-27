import com.example.demo.SimpleServer;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaxServiceIntegrationTest {

    private static final int PORT = 8080;

    @BeforeAll
    static void setup() {
        new SimpleServer().startServer(PORT);
        RestAssured.port = PORT;
        RestAssured.baseURI = "http://localhost";
        RestAssured.defaultParser = Parser.JSON;

        RestAssured.config = config()
                .jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE));
    }

    @AfterAll
    static void teardown(){
        SimpleServer.stop();
    }

    @Test
    public void simpleIntegrationTest(){
        System.out.println("Running simpleIntegrationTest test...");
        SimpleServer simpleServer = new SimpleServer();
        assertNotNull(simpleServer);
    }

    @Test
    public void rootPath(){
        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body(containsString("<html"));
    }

    @Test
    public void taxPath(){
        given()
                .when()
                .get("/tax")
                .then()
                .statusCode(200)
                .body("size()", equalTo(15))
                .body("[0].beforeIncome", closeTo(0.0, 1e-4));
    }

}