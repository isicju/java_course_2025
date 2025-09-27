import com.example.demo.TaxService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaxServiceUnitTest {
    final double LESS_PERSONAL_ALLOWANCE = 10000.0;
    final double LESS_BASIC_RATE_THRESHOLD = 40000.0;
    final double LESS_HIGHER_RATE_THRESHOLD = 100000.0;
    final double MORE_HIGHER_RATE_THRESHOLD = 130000.0;

    @Test
    public void testLessPersonalAllowanceValue(){
        System.out.println("Running testLessPersonalAllowanceValue test...");
        TaxService taxService = new TaxService();
        assertEquals(827.4133333333333, taxService.getNetMonthlySalary(LESS_PERSONAL_ALLOWANCE));
    }

    @Test
    public void testLessBasicRateThresholdValue(){
        System.out.println("Running testLessBasicRateThresholdValue test...");
        TaxService taxService = new TaxService();
        assertEquals(2670.2466666666664, taxService.getNetMonthlySalary(LESS_BASIC_RATE_THRESHOLD));
    }

    @Test
    public void testLessHigherRateThresholdValue(){
        System.out.println("Running testLessHigherRateThresholdValue test...");
        TaxService taxService = new TaxService();
        assertEquals(5690.063333333333, taxService.getNetMonthlySalary(LESS_HIGHER_RATE_THRESHOLD));
    }

    @Test
    public void testMoreHigherRateThresholdValue(){
        System.out.println("Running testMoreHigherRateThresholdValue test...");
        TaxService taxService = new TaxService();
        assertEquals(6910.313333333333, taxService.getNetMonthlySalary(MORE_HIGHER_RATE_THRESHOLD));
    }
}
