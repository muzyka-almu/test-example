package com.example.testing;

import com.example.testing.dao.UserDao;
import com.example.testing.model.User;
import com.example.testing.service.TestService;
import com.example.testing.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestingMockitoTests {
	@Mock
	UserDao userDao;

	@Spy
	UserDao userDao1;

	@Test
	public void contextLoads() {
		// databaseMock: null
		System.out.println("databaseMock: " + userDao.findById(1));
		// UserDao.findById
		// databaseMock: com.example.jpaquerydslexample.User@6a78afa0
		System.out.println("databaseMock: " + userDao1.findById(1));
	}

	/*
	OUT:
		before invoke
		after invoke
	 */
	@Test
	public void test1()  {
		UserDao ud = mock(UserDao.class);
		User fakeUser1 = new User(2, 23, "ApplicationTests.test1");
		User fakeUser2 = new User(3, 23, "ApplicationTests.test1");

		when(ud.findById(1)).thenReturn(fakeUser1).thenReturn(fakeUser2);

		System.out.println("before invoke");

		assertEquals(ud.findById(1), fakeUser1);
		assertEquals(ud.findById(1), fakeUser2);

		System.out.println("after invoke");
	}

	@Test
	public void test2()  {
		UserDao ud = mock(UserDao.class);
		User fakeUser1 = new User(2, 23, "ApplicationTests.test2");

		when(ud.save(isA(User.class))).thenReturn(true); // .thenThrow
//		fail("Should fail");

		assertEquals(ud.save(new User()), true);
	}

	@Test
	public void test3()  {
		/*
		OUT:
			UserDao.save
			>> 1
			>> 2
		 */
//		UserDao ud = spy(UserDao.class);
//		User fakeUser1 = new User(2, 23, "ApplicationTests.test2");
//
//		when(ud.save(isA(User.class))).thenReturn(true);
//		System.out.println(">> 1");
//		assertEquals(ud.save(new User()), true);
//		System.out.println(">> 2");


		/*
		OUT:
			>> 1
			>> 2
		 */
		UserDao ud = spy(UserDao.class);
		User fakeUser1 = new User(2, 23, "ApplicationTests.test2");

		doReturn(true).when(ud).save(isA(User.class));
		System.out.println(">> 1");
		assertEquals(ud.save(new User()), true);
		System.out.println(">> 2");
	}


	@Test
	public void test4()  {
		UserDao ud = mock(UserDao.class);
		User fakeUser = new User(2, 23, "ApplicationTests.test4");

		when(ud.findById(1)).thenReturn(fakeUser).thenReturn(fakeUser);

		assertEquals(ud.findById(1), fakeUser);
		verify(ud, times(1)).findById(ArgumentMatchers.eq(1));
		/*
			verify(ud, never()).someMethod("never called");
			verify(ud, atLeastOnce()).someMethod("called at least once");
			verify(ud, atLeast(2)).someMethod("called at least twice");
			verify(ud, times(5)).someMethod("called five times");
			verify(ud, atMost(3)).someMethod("called at most 3 times");
			// You call it after you have verified the expected method calls.
			verifyNoMoreInteractions(test);
		 */
	}

	@Mock
	public TestService testService;
	// If not all constructor args have mock instance will set null value
	@InjectMocks
	public UserService userService;

	/*
	OUT:
		UserService.save
		UserService.save.userDao com.example.testing.dao.UserDao$MockitoMock$564687965@1aafa419
		UserService.save.testService testService
		test5
	 */
	@Test
	public void test5() {
		userService.save();
		System.out.println("test5 ");
	}

	/*
	OUT:
		Fake logic here!!!!
		User1 name:Fake name
	 */
	@Test
	public void test6() {
		UserDao ud = mock(UserDao.class);

		when(ud.getNameById(anyInt())).thenAnswer(i -> {
			System.out.println("Fake logic here!!!!");

			return "Fake name";
		}); // returnsFirstArg()

		System.out.println("User1 name:" + ud.getNameById(1));
	}

	// TODO it is possible mocking final classes
	// Cannot mock static methods and private methods
	// https://github.com/mockito/mockito/wiki/Mockito-And-Private-Methods
}
