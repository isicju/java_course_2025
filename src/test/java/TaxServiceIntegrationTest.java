import com.example.demo.SimpleServer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.demo.WebUtils.HTML_TEMPLATE;
import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaxServiceIntegrationTest {

    @Test
    public void complexIntegrationTest(){
        System.out.println("Running complexIntegrationTest test...");

        // Start Http Server
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.startServer(8080);

        assertNotNull(simpleServer);

        //HTML
        get("/").then().statusCode(200);
        String responseHtml = get("/").asString();
        assertTrue(responseHtml.equals(HTML_TEMPLATE));
        System.out.println("Получили ожидаемый ответ по запросу / (HTML)");

        // Http Requests
        // JSON
        List<Float> expectedBeforeIncomeList =
                List.of(0.0f, 10000.0f, 20000.0f, 30000.0f, 40000.0f, 50000.0f, 60000.0f,
                        70000.0f, 80000.0f, 90000.0f, 100000.0f, 110000.0f, 120000.0f, 130000.0f, 140000.0f);

        Response response = get("/tax").then().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<Float> beforeIncomeList = jsonPath.getList("beforeIncome");
        assertTrue(expectedBeforeIncomeList.equals(beforeIncomeList));

        List<Float> expectedAfterIncomeList = List.of(0.0f, 827.4133333333333f, 1470.2466666666667f, 2070.2466666666664f,
                2670.2466666666664f, 3270.2466666666664f, 3756.73f, 4240.0633333333335f, 4723.3966666666665f,
                5206.7300000000005f, 5690.063333333333f, 6090.063333333333f, 6490.063333333333f,
                6910.313333333333f, 7351.98f);

        List<Float> afterIncomeList = jsonPath.getList("afterIncome");
        assertTrue(expectedAfterIncomeList.equals(afterIncomeList));

        System.out.println("Получили ожидаемый ответ по запросу /tax (JSON)");


        // Stop Http Server
        SimpleServer.stop();
        System.out.println("Simple Server stopped.");
    }
}
