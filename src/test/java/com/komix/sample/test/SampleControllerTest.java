package com.komix.sample.test;

import com.komix.sample.StandaloneApplication;
import com.komix.sample.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by krepela on 15.4.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StandaloneApplication.class, WebApplication.class})
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldContainGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/Novak")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mr. Novak")));
    }

    @Test
    public void shouldContainCorrectDateFormat() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/Novak")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", hasSize(7)));
    }

}
