import java.util.HashMap;
import java.util.Stack;

public class JsonParser {
    HashMap<String, Object> fields;

    public JsonParser() {
        fields = new HashMap<>();
    }

    public JsonParser(HashMap<String, Object> fields) {
        this.fields = fields;
    }

    public void Parse(String json) {

        StringBuilder sb = new StringBuilder(json);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        char[] array = sb.toString().toCharArray();

        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (c == ' ') {
                continue;
            } else if (c == ':') {
                for (int j = i-1; ) {

                }
            }
        }
        System.out.println(sb);

    }
    /**
     * stack: ['{', '"']
     * {"a":"b"}
     *   ^
     * 1. push '{' to stack
     * 2. push '"' to stack
     */


}