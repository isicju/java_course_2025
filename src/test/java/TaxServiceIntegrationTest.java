import com.example.demo.SimpleServer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

public class TaxServiceIntegrationTest {

    @DisplayName("Testing that tax api returns 200 and right tax json data")
    @Test
    public void simpleIntegrationTestRestAssured() {
        System.out.println("Running simpleIntegrationTest test...");
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.startServer(3000);

        RestAssured.baseURI = "http://localhost:3000";

        given()
                .when()
                .get("/tax")
                .then()
                .statusCode(200)
                .body(containsString("{\"beforeIncome\":140000.0,\"afterIncome\":7351.98}"));

        simpleServer.stop();
    }

    @DisplayName("Testing that html web returns with 200 code and not empty body")
    @Test
    public void htmlCallTestRestAssured() throws IOException {
        System.out.println("Running running integration test checking web api");
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.startServer(3000);
        RestAssured.baseURI = "http://localhost:3000";

        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body(containsString("<html lang=\"en\">"));
    }

}
