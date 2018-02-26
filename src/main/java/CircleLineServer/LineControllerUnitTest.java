package CircleLineServer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LineController.class, secure = false)
public class LineControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public Lines lines;

//    private static HashMap<Integer, String> allLines = new HashMap<>();
//    allLines.put(1,"one");

    // Tests that the correct response is received when asking for a line that doesn't exist.
    @Test
    public void retrieveLineOutOfRange() throws Exception {

        //Mockito.when(lines.insertLine(Mockito.anyInt(), Mockito.anyString()));

        // Build a request call that will call /lines/1
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lines/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "Status Code: 413 Payload to large. File does not contain that line number.";

        // Expecting a 413 because we requested line 1 when the hashmap is unpopulated
        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void retrieveLine() throws Exception {
        Mockito.when(lines.insertLine(Mockito.anyInt(), Mockito.anyString()));

        // Build a request call that will call /lines/1
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lines/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "Status Code: 200 one";

        // Expecting a 413 because we requested line 1 when the hashmap is unpopulated
        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }
    }
}
