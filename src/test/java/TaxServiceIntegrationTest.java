import com.example.demo.SimpleServer;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaxServiceIntegrationTest {

    private static final String URL = "http://localhost:81/%s";

    private SimpleServer simpleServer;

    @DisplayName("Stop server after each test")
    @AfterEach
    public void stopServer() {
        if (simpleServer != null) {
            try (HttpClient client = HttpClient.newBuilder().build()) {
                HttpRequest request = HttpRequest
                        .newBuilder(URI.create(String.format(URL, "stop")))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                assertEquals(response.statusCode(), 200);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @DisplayName("Test status code from /tax is 200")
    @Test
    public void testStatusCode(){
        simpleServer = new SimpleServer();
        simpleServer.startServer(81);
        assertNotNull(simpleServer);
        try (HttpClient client = HttpClient.newBuilder().build()) {
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(String.format(URL, "tax")))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(response.statusCode(), 200);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Test return value from /tax")
    @Test
    public void testJsonContent(){
        simpleServer = new SimpleServer();
        simpleServer.startServer(81);
        assertNotNull(simpleServer);

        when()
                .get(String.format(URL, "tax"))
        .then()
                .using()
                .defaultParser(Parser.JSON)
                .body("$", hasSize(15))
                .body("beforeIncome", hasItem(10000.0f))
                .body("[14].beforeIncome", equalTo(140000.0f))
                .body("[14].afterIncome",  equalTo(7351.98f));

    }

    @DisplayName("Test status code from / is 200")
    @Test
    public void testStatusCodeHomePage(){
        simpleServer = new SimpleServer();
        simpleServer.startServer(81);
        assertNotNull(simpleServer);
        when()
                .get(String.format(URL, ""))
        .then()
                .statusCode(200);
    }


}
