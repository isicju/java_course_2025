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

    @Test
    public void grossLowerPersonalAllowanceButHigherNILowerThreshold(){
        double gross = 10000;
        double expectedIncomeTax = 0.0;
        double expectedIN = (gross - 9112.0) * 0.08;
        double expectedNetMonthly = (gross - expectedIN - expectedIncomeTax) / 12;

        double actual = new TaxService().getNetMonthlySalary(gross);

        assertEquals(expectedNetMonthly, actual, 0.01);
    }

    @Test
    public void rangeBasicHigher(){
        double gross = 50270.0;
        double taxable = gross - 12570.0;
        double expectedIncomeTax = taxable * 0.2;
        double expectedNI = (50270 - 9112.0) * 0.08;
        double expectedNetMonthly = (gross - expectedIncomeTax - expectedNI) / 12.0;

        double actual = new TaxService().getNetMonthlySalary(gross);

        assertEquals(actual, expectedNetMonthly, 0.01);
    }

}
