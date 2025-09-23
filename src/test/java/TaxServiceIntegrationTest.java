import com.example.demo.SimpleServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public class TaxServiceIntegrationTest {

    @Test
    public void testStatusCode(){
        System.out.println("Running simpleIntegrationTest test...");
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.startServer(80);
        assertNotNull(simpleServer);
        try (HttpClient client = HttpClient.newBuilder().build()) {
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create("http://localhost:80/tax"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(response.statusCode(), 200);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testJsonContent(){
        System.out.println("Running simpleIntegrationTest test...");
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.startServer(80);
        assertNotNull(simpleServer);
        try (HttpClient client = HttpClient.newBuilder().build()) {
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create("http://localhost:80/tax"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(response.body(), "[{\"beforeIncome\":0.0,\"afterIncome\":0.0},{\"beforeIncome\":10000.0,\"afterIncome\":827.4133333333333},{\"beforeIncome\":20000.0,\"afterIncome\":1470.2466666666667},{\"beforeIncome\":30000.0,\"afterIncome\":2070.2466666666664},{\"beforeIncome\":40000.0,\"afterIncome\":2670.2466666666664},{\"beforeIncome\":50000.0,\"afterIncome\":3270.2466666666664},{\"beforeIncome\":60000.0,\"afterIncome\":3756.73},{\"beforeIncome\":70000.0,\"afterIncome\":4240.0633333333335},{\"beforeIncome\":80000.0,\"afterIncome\":4723.3966666666665},{\"beforeIncome\":90000.0,\"afterIncome\":5206.7300000000005},{\"beforeIncome\":100000.0,\"afterIncome\":5690.063333333333},{\"beforeIncome\":110000.0,\"afterIncome\":6090.063333333333},{\"beforeIncome\":120000.0,\"afterIncome\":6490.063333333333},{\"beforeIncome\":130000.0,\"afterIncome\":6910.313333333333},{\"beforeIncome\":140000.0,\"afterIncome\":7351.98}]");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
