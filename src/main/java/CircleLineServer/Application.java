package CircleLineServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

@SpringBootApplication
public class Application {
    public static HashMap<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            // Open new input stream to file provided by user
            inputStream = new FileInputStream(args[0]);

            // Specified UTF-8 encoding to support all valid ASCII characters
            sc = new Scanner(inputStream, "UTF-8");

            int i =1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // Insert each line into a hashMap with <key>=line number & <value>=text on line
                map.put(i,line);
                i++;
            }

            // Scanner suppresses exceptions so check for ioException
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            // Close inputStream and scanner
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        // Launch application
        SpringApplication.run(Application.class, args);
    }
}