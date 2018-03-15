package com.example.testing;

import com.example.testing.controller.HelloController;
import com.example.testing.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class TestingMvcTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    TestService testService;

    @Test
    public void testRootApi() throws Exception {
        this.mvc.perform(get("/")/*.accept(MediaType.TEXT_PLAIN)*/)
                .andExpect(status().isOk()).andExpect(content().string("Hello world"));
    }
}
