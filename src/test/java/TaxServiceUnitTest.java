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

    @Test
    public void test40k(){
        System.out.println("Running test40k test...");
        TaxService taxService = new TaxService();
        assertEquals(2670.2466666666664d, taxService.getNetMonthlySalary(40_000));
    }

    @Test
    public void test70k(){
        System.out.println("Running test70k test...");
        TaxService taxService = new TaxService();
        assertEquals(4240.0633333333335d, taxService.getNetMonthlySalary(70_000));
    }

    @Test
    public void test100k(){
        System.out.println("Running test100k test...");
        TaxService taxService = new TaxService();
        assertEquals(5690.063333333333d, taxService.getNetMonthlySalary(100_000));
    }

}
