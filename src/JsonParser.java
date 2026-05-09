import java.text.ParseException;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;

public class JsonParser {
    HashMap<String, Object> map;

    public JsonParser() {
        map = new HashMap<>();
    }

    public JsonParser(HashMap<String, Object> fields) {
        this.map = fields;
    }

    public void Parse(String json) throws Exception {
        if (json.charAt(0) == '[') {
            parseArray(json);
        } else if (json.charAt(0) == '{') {
            parseCollection(json);
        }
    }

    private void parseKVPair(String kvString) throws Exception {
        /**
         * "key1":"value1"
         * "key2":[]
         * "key3":{}
         * "key4":1
         * "key5":false
         * "key6:null
         * "key6:undefined
         */

        String[] kvPair = kvString.split(":");
        String key = kvPair[0];
        String value = kvPair[1];

        if (key.charAt(0) != '"' || key.charAt(key.length()-1) != '"') {
            throw new Exception("Invalid key value pair");
        }

        if (value.charAt(0) == '{') {
            parseCollection(value);
        } else if (value.charAt(0) == '[') {
            parseArray(value);
        } else if (value.charAt(0) == '"') {
            map.put(key.substring(1, key.length()-1), value.substring(1, value.length()-1));
        } else if (Pattern.matches("[0-9]", value)) {
            map.put(key.substring(1, key.length()-1), Integer.parseInt(value));
        } else if (Pattern.matches("true|false", value)) {
            map.put(key.substring(1, key.length()-1), value.equals("true"));
        } else if (Pattern.matches("null|undefined", value)) {
            map.put(key.substring(1, key.length()-1), null);
        }
    }

    private Object[] parseArray(String v) throws Exception {
        Object[] arr = new Object[]{};
        if (v.charAt(0) != '[' || v.charAt(v.length()-1) != ']') {
            throw new Exception("Invalid array format");
        }

        StringBuilder sb = new StringBuilder(v);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length()-1);
        /**
         * ["a",1,true,["aa","bb"],{"a":"b"}]
         * "a",1,true,["aa","bb"],{"a":"b"}
         * i
         *    k
         */
        boolean arrayOrObjectFound = false;

        for (int i = 0; i < sb.length(); i++) {
            // We need to handle mixed primitives e.g ["A", 2, true]
            if (sb.charAt(i) == '[' || sb.charAt(i) == '{') {
                arrayOrObjectFound = true;
                for ()
            } else if (sb.charAt(i) == '"') {
                int k = i;
                while (sb.charAt(k) != ',' && k < sb.length()) {
                    k++;
                }
                sb.substring(i+1, k-2);
                i = k+1;
            } else if(Pattern.matches("true|false", sb)) {}
        }
    }

    private void parseCollection(String v) throws Exception {
        if (v.charAt(0) != '{' || v.charAt(v.length()-1) != '}') {
            throw new Exception("Invalid object format");
        }

        StringBuilder jsonSb = new StringBuilder(v);
        jsonSb.deleteCharAt(0);
        jsonSb.deleteCharAt(jsonSb.length()-1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < jsonSb.length(); i++) {
            char c = jsonSb.charAt(i);
            if (c == ' ') {
                continue;
            } else if (c == ',') {
                parseKVPair(sb.toString());
                sb = new StringBuilder();
            }
            sb.append(c);
        }
    }
}