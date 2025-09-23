import com.example.demo.SimpleServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaxServiceIntegrationTest {

    @Test
    public void simpleIntegrationTest(){
        System.out.println("Running simpleIntegrationTest test...");
        SimpleServer simpleServer = new SimpleServer();
        assertNotNull(simpleServer);
    }

    @Test
    public void apiHandlerIntegrationTest() throws IOException, InterruptedException {
        System.out.println("Running apiHandlerIntegrationTest test...");
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.startServer(80);

        var expectedJson = "[{\"beforeIncome\":0.0,\"afterIncome\":0.0},{\"beforeIncome\":10000.0,\"afterIncome\":827.4133333333333},{\"beforeIncome\":20000.0,\"afterIncome\":1470.2466666666667},{\"beforeIncome\":30000.0,\"afterIncome\":2070.2466666666664},{\"beforeIncome\":40000.0,\"afterIncome\":2670.2466666666664},{\"beforeIncome\":50000.0,\"afterIncome\":3270.2466666666664},{\"beforeIncome\":60000.0,\"afterIncome\":3756.73},{\"beforeIncome\":70000.0,\"afterIncome\":4240.0633333333335},{\"beforeIncome\":80000.0,\"afterIncome\":4723.3966666666665},{\"beforeIncome\":90000.0,\"afterIncome\":5206.7300000000005},{\"beforeIncome\":100000.0,\"afterIncome\":5690.063333333333},{\"beforeIncome\":110000.0,\"afterIncome\":6090.063333333333},{\"beforeIncome\":120000.0,\"afterIncome\":6490.063333333333},{\"beforeIncome\":130000.0,\"afterIncome\":6910.313333333333},{\"beforeIncome\":140000.0,\"afterIncome\":7351.98}]";

        var req = HttpRequest.newBuilder(URI.create("http://localhost:80/tax")).GET().build();
        var httpClient = HttpClient.newHttpClient();
        HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        var actualJson = resp.body();

        assertEquals(200, resp.statusCode());
        assertEquals(expectedJson, actualJson);
    }

}
