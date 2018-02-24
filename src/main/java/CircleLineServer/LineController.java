package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineController {

    private static final String template = "Serving up line number %s";

    @RequestMapping("/lines")
    public String line(@RequestParam(value="line", defaultValue="0") Integer line) {
        return Application.map.get(line);
    }
}