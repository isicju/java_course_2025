import com.example.demo.SimpleServer;
import com.example.demo.TaxService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxServiceUnitTest {

    @Test
    public void testEmptyValue(){
        System.out.println("Running testEmptyValue test...");
        TaxService taxService = new TaxService();
        assertEquals(0, taxService.getNetMonthlySalary(0));
    }

}
