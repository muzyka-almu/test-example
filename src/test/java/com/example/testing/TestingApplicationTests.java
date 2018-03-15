package com.example.testing;

import com.example.testing.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingApplicationTests {

//	@TestConfiguration
//	static class MyTestsConfig {
//		@Bean
//		public TestService testService() {
//			TestService testService = new TestService();
//			testService.setData("custom test data");
//
//			return testService;
//		}
//	}

	@Autowired
	TestService testService;

	@Test
	public void contextLoads() {
		// OUT: In test:default data
		// OUT: In test:custom test data, if uncomment @TestConfiguration
		System.out.println("In test:" + testService.getData());
	}
}
