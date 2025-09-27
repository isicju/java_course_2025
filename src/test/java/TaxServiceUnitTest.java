import com.example.demo.SimpleServer;
import com.example.demo.TaxService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaxServiceUnitTest {

    @Test
    public void testEmptyValue() {
        System.out.println("Running testEmptyValue test...");
        TaxService taxService = new TaxService();
        assertEquals(0, taxService.getNetMonthlySalary(0));
    }

    @DisplayName("Covering case with 10k per year, expected monthly salary after tax 827")
    @Test
    public void test10K() {
        System.out.println("Running testEmptyValue test...");
        TaxService taxService = new TaxService();
        double delta = taxService.getNetMonthlySalary(10000) - 827;
        double acceptableDifference = 1;
        assertTrue(delta < acceptableDifference);
    }

    @DisplayName("Covering case with 15k per year, expected monthly salary after tax 1170")
    @Test
    public void test15K() {
        System.out.println("Running testEmptyValue test...");
        TaxService taxService = new TaxService();
        double delta = taxService.getNetMonthlySalary(15000) - 1170;
        double acceptableDifference = 1;
        assertTrue(delta < acceptableDifference);
    }

}
