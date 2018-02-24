package CircleLineServer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LineController {

    @RequestMapping(value = "/lines/{lineNum}", method= RequestMethod.GET)
    @ResponseBody
    public String line(@PathVariable("lineNum") Integer lineNum){
        String text = Application.map.get(lineNum);
        if(text == null){
            return String.valueOf("Status Code: " + HttpStatus.PAYLOAD_TOO_LARGE);
        }
        return "Status Code: " + HttpStatus.ACCEPTED + String.format("%n%n") + Application.map.get(lineNum);
    }

    @RequestMapping(value = "*")
    @ResponseBody
    public String allFallback() {
        return "Invalid query. Please structure your query as follows: http://localhost:8080/lines/{line number}";
    }
}