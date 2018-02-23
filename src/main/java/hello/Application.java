package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        File file = new File(args[0]);
        System.out.print("FileName: " + file);

        //Parse file and input each line into a map with <key>=line number & <value>=text on line

        SpringApplication.run(Application.class, args);
    }
}