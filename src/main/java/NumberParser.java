import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class NumberParser {
    private Map<String, Integer> countryCodes;
    private Map<Integer, String> reversedCountryCodes;
    private Map<String, String> nationalTrunkPrefixes;

    public NumberParser(Map<String, Integer> countryCodes, Map<String, String> nationalTrunkPrefixes) {
        this.countryCodes = countryCodes;
        this.nationalTrunkPrefixes = nationalTrunkPrefixes;
        this.reversedCountryCodes = reverseCountryCodes(countryCodes);
    }

    private Map<Integer,String> reverseCountryCodes(Map<String, Integer> countryCodes) {
        Map<Integer,String> codes = new HashMap<Integer, String>();

        Iterator<Entry<String, Integer>> iterator
            = countryCodes.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> map
                = (Map.Entry<String, Integer>)
                      iterator.next();
            codes.putIfAbsent(map.getValue(), map.getKey());
        }
        return codes;
    }

    public String parse(String dialledNumber, String userNumber) {
        dialledNumber = dialledNumber.replaceAll("\\s+","");
        if (dialledNumber.startsWith("+")) {
            return dialledNumber;
        }

        userNumber = userNumber.replaceAll("\\s+","");

        String userLocation = getUserLocation(userNumber);

        return "+" + countryCodes.get(userLocation) + dialledNumber.substring((nationalTrunkPrefixes.get(userLocation)).length());
    }

    private String getUserLocation(String userNumber){
        for (int i = 0; i < 4; i++) { // Assuming max country code length of 4
            int possibleCountryCode = Integer.parseInt(userNumber.substring(1, i + 2));
            String value = this.reversedCountryCodes.get((possibleCountryCode));
            if (value != null) {
                return value;
            }
        }
        return "";
    }
}
