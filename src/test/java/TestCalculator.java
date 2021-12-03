import org.junit.Assert;
import org.junit.Test;

public class TestCalculator {
    @Test
    public void testAdd(){
        Assert.assertEquals(4, Calculator.add(1, 3));
    }
}
