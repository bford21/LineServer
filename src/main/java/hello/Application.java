package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {

        //Parse file and input each line into a map with <key>=line number & <value>=text on line
        FileInputStream inputStream = null;
        Scanner sc = null;
        Map<Integer, String> map = new HashMap<>();
        int i =1;
        try {
            inputStream = new FileInputStream(args[0]);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                map.put(i,line);
                i++;
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        SpringApplication.run(Application.class, args);
    }
}