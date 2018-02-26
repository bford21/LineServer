package CircleLineServer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LineController.class, secure = false)
public class LineControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    //public Lines lines;

    // Tests that the correct response is received when asking for a line that doesn't exist.
    @Test
    public void retrieveLineOutOfRange() throws Exception {

        // Build a request call that will call /lines/1
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lines/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "Status Code: 413 Payload to large. File does not contain that line number.";

        // Never populated the Lines class with data so expecting 413
        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }

    // Tests that correct response is received when sending a query for an existing line
    @Test
    public void retrieveLine() throws Exception {

        Lines lines = mock(Lines.class);

        Mockito.doNothing().when(lines).insertLine(1,"one");
        lines.insertLine(1,"one");

        // Build a request call that will call /lines/1
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lines/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "Status Code: 200<br />one";

        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }
}
