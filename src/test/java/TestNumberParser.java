import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNumberParser {

    private Map<String, Integer> countryCodes;
    private Map<String, String> nationalTrunkPrefixes;

    private NumberParser parser;

    @Before
    public void before() {
        countryCodes = new HashMap<String, Integer>();
        countryCodes.put("UK", 44);
        countryCodes.put("FAKE", 43);
        countryCodes.put("FR", 33);
        countryCodes.put("US", 1);
        countryCodes.put("HK", 852);

        nationalTrunkPrefixes = new HashMap<String, String>();
        nationalTrunkPrefixes.put("UK", "0");
        nationalTrunkPrefixes.put("FAKE", "136");
        nationalTrunkPrefixes.put("FR", "0");
        nationalTrunkPrefixes.put("US", "1");
        nationalTrunkPrefixes.put("HK", "");

        parser = new NumberParser(countryCodes, nationalTrunkPrefixes);
    }

    @Test
    public void testReverseCountryCodes(){
        Map<Integer, String> expected = new HashMap<Integer, String>();
        expected.put(44, "UK");
        expected.put(43, "FAKE");
        expected.put(33, "FR");
        expected.put(1, "US");
        expected.put(852, "HK");

        Assert.assertEquals(expected, parser.getReversedCountryCodes());
    }

    @Test
    public void testParseUkToUk(){ // Test case from task
        String dialledNumber = "07277822334";
        String userNumber = "+447866866886";

        String expected = "+447277822334";
        Assert.assertEquals(expected, parser.parse(dialledNumber, userNumber));
    }

    @Test
    public void testParseUsToUs(){ // Test case from task
        String dialledNumber = "1312233244";
        String userNumber = "+1212233200";

        String expected = "+1312233244";
        Assert.assertEquals(expected, parser.parse(dialledNumber, userNumber));
    }

    @Test
    public void testParseUkToUs(){ // Test case from task
        String dialledNumber = "+1312233244";
        String userNumber = "+447866866886";

        String expected = "+1312233244";
        Assert.assertEquals(expected, parser.parse(dialledNumber, userNumber));
    }
}



