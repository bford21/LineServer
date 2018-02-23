package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineController {

    private static final String template = "Serving up line number %s";

    @RequestMapping("/lines")
    public Line line(@RequestParam(value="line", defaultValue="0") String line) {
        return new Line(String.format(template, line));
    }
}