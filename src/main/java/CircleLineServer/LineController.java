package CircleLineServer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LineController {

    @RequestMapping(value = "/lines/{lineNum}", method= RequestMethod.GET)
    @ResponseBody
    public String line(@PathVariable("lineNum") Integer lineNum){
        // Retrieve line
        String text = Lines.getLine(lineNum);

        // Check if line is null and then return HTTP Status code 413 to indicate the file doesn't contain that line
        if(text == null)
            return String.valueOf(String.format("Status Code: %s Payload to large. File does not contain that line number.", HttpStatus.PAYLOAD_TOO_LARGE));

        // Return contents of line and HTTP Status code 200
        return String.format("Status Code: %s<br />%s", HttpStatus.OK, text);
    }

    // Request mapping to handle all other routes
    @RequestMapping(value = "*")
    @ResponseBody
    public String allFallback() {
        return "Invalid query. Please structure your query as follows: http://localhost:8080/lines/{line number}";
    }
}