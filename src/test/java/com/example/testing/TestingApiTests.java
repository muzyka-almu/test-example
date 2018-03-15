package com.example.testing;

import com.example.testing.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
//import static org.assertj.core.api.Assertions.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT /*start a full running server*/)
public class TestingApiTests {

    @LocalServerPort
    private Integer port;

//    TODO don't work now, but should (https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-with-running-server)
//    @Autowired
//    private WebTestClient webClient;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCurrentPort() {
        System.out.println("port:" + port);
//        this.webClient.get().uri("/").exchange().expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("Hello world");
    }

    @Test
    public void testRootPath() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body, equalTo("Hello world"));
    }

    @MockBean
    // @SpyBean
    private TestService testService;

    @Test
    public void exampleTest() {
        String fakeData = "TestService is MockBean";
        given(testService.getData()).willReturn(fakeData);
        assertThat(testService.getData(), equalTo(fakeData));
    }
}
