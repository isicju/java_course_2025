import com.example.demo.TaxService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaxServiceIntegrationTest {

    @Test
    public void simpleIntegrationTest(){
        System.out.println("Running simpleIntegrationTest test...");
        TaxService taxService = new TaxService();
        assertNotNull(taxService);
    }

}
