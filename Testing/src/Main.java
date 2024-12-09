import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[] data = new String[] {"Test1", "678", "hello123"};
        StringBuilder temp = new StringBuilder();
        for (String text : data) {
            temp.append(text).append(", ");
        }
        temp.delete(temp.length() - 2, temp.length()).append("\n");
        try {


            File file = new File("data.csv");
            boolean fileFlag = file.createNewFile();
            FileWriter writer = new FileWriter(file, true);
            if (fileFlag) {
                writer.write("Hello, 123, data\n");
            }
            writer.write(temp.toString());
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}