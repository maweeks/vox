import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class NumberParser {
    private Map<String, Integer> countryCodes;
    private Map<Integer, String> reversedCountryCodes;
    private Map<String, String> nationalTrunkPrefixes;
    private int maxCountryCodeLength;

    public NumberParser(Map<String, Integer> countryCodes, Map<String, String> nationalTrunkPrefixes) {
        this.countryCodes = countryCodes;
        this.nationalTrunkPrefixes = nationalTrunkPrefixes;
        this.maxCountryCodeLength = 0;
        this.reversedCountryCodes = reverseCountryCodes(countryCodes);

    }

    public Map<Integer, String> getReversedCountryCodes() {
        return this.reversedCountryCodes;
    }

    private Map<Integer,String> reverseCountryCodes(Map<String, Integer> countryCodes) {
        Map<Integer,String> codes = new HashMap<Integer, String>();
        int maxCountryCode = 0;

        Iterator<Entry<String, Integer>> iterator
            = countryCodes.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> map
                = (Map.Entry<String, Integer>)
                    iterator.next();
            codes.putIfAbsent(map.getValue(), map.getKey());

            if (map.getValue() > maxCountryCode) {
                maxCountryCode = map.getValue();
            }

        }

        this.maxCountryCodeLength = Integer.toString(maxCountryCode).length();

        return codes;
    }

    public String parse(String dialledNumber, String userNumber) {
        dialledNumber = dialledNumber.replaceAll("\\s+","");
        if (dialledNumber.startsWith("+")) {
            return dialledNumber;
        }

        userNumber = userNumber.replaceAll("\\s+","");

        String userLocation = getUserLocation(userNumber);
        if (userLocation != "") {
            return "+" + countryCodes.get(userLocation) + dialledNumber.substring((nationalTrunkPrefixes.get(userLocation)).length());
        }
        return dialledNumber;
    }

    private String getUserLocation(String userNumber){
        for (int i = 0; i < this.maxCountryCodeLength; i++) {
            int possibleCountryCode = Integer.parseInt(userNumber.substring(1, i + 2));
            String value = this.reversedCountryCodes.get((possibleCountryCode));
            if (value != null) {
                return value;
            }
        }
        return "";
    }
}
