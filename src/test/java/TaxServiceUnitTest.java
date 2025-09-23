import com.example.demo.SimpleServer;
import com.example.demo.TaxService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxServiceUnitTest {

    @Test
    public void testEmptyValue(){
        System.out.println("Running testEmptyValue test...");
        TaxService taxService = new TaxService();
        assertEquals(0, taxService.getNetMonthlySalary(0));
    }

    @Test
    public void test10000Gross(){
        TaxService taxService = new TaxService();
        BigDecimal salary = BigDecimal.valueOf(taxService.getNetMonthlySalary(10000));
        assertEquals(827.413, salary.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @Test
    public void test100000Gross(){
        TaxService taxService = new TaxService();
        BigDecimal salary = BigDecimal.valueOf(taxService.getNetMonthlySalary(100000));
        assertEquals(5690.063, salary.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }

    @Test
    public void test140000Gross(){
        TaxService taxService = new TaxService();
        BigDecimal salary = BigDecimal.valueOf(taxService.getNetMonthlySalary(140000));
        assertEquals(7351.98, salary.setScale(3, RoundingMode.HALF_UP).doubleValue());
    }


}
