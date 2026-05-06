import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        JsonParser parser = new JsonParser();
        String path = "./src/datasets/data_small.json";
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();

        try {
            fr = new FileReader(path);
            int charData;
            while((charData = fr.read()) != -1) {
                if (charData != '\n') {
                    sb.append((char) charData);
                }
            }
            parser.Parse(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
