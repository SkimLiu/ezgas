package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.service.impl.UserServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest { 

	@Test
	public void testGetUserByIdRightValue() {
			
		UserService userService;
		User u = new User("username", "password", "email", 5);
		u.setAdmin(false);
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(u);
			
		userService = new UserServiceimpl(test);
		
		try {
			
			UserDto result = userService.getUserById(anyInt());
			assert(result.getReputation() == u.getReputation());
			assertFalse(result.getAdmin() == true); }
		
		catch(InvalidUserException e) {
			fail("unexpected Exception raised in getUserById");
		}

	}
	
	@Test(expected = InvalidUserException.class)
	public void testGetUserByIdNullValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
			
		userService = new UserServiceimpl(test);
		userService.getUserById(null);

	}
	
	@Test(expected = InvalidUserException.class)
	public void testGetUserByIdNegativeValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
			
		userService = new UserServiceimpl(test);
		userService.getUserById(-1);
	}
	
	@Test
	public void testGetUserByIdNoUserFound() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(null);
			
		userService = new UserServiceimpl(test);
		userService.getUserById(anyInt());
	}
	
	@Test
	public void testGetUsersZero() {
		
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findAll()).thenReturn(new ArrayList<User>());
		
		userService = new UserServiceimpl(test);
		
		ArrayList<UserDto> gsarray = (ArrayList<UserDto>) userService.getAllUsers();
		assert(gsarray.size() == 0); 
	
	}
	
	public void testGetUsersOne() {
		
		UserService userService;
		List<User> list = new ArrayList<User>();
		list.add(new User());
			
		UserRepository test = mock(UserRepository.class);
		when(test.findAll()).thenReturn(list);
		
		userService = new UserServiceimpl(test);
		
		ArrayList<UserDto> gsarray = (ArrayList<UserDto>) userService.getAllUsers();
		assert(gsarray.size() == 1);
	
	}
	
	public void testGetUsersTwo() {
		
		UserService userService;
		ArrayList<User> list = new ArrayList<User>();
		list.add(new User());
		list.add(new User());
			
		UserRepository test = mock(UserRepository.class);
		when(test.findAll()).thenReturn(list);
		
		userService = new UserServiceimpl(test);
		
		ArrayList<UserDto> gsarray = (ArrayList<UserDto>) userService.getAllUsers();
		
		assert(gsarray.size() == 2);
	}

	
	@Test(expected = InvalidUserException.class)
	public void testDeleteUserByIdNullValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
		doNothing().when(test).delete(anyInt());
			
		userService = new UserServiceimpl(test);
		userService.deleteUser(null);

	}

	@Test(expected = InvalidUserException.class)
	public void testDeleteUserByIdNegativeValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
		doNothing().when(test).delete(anyInt());
		
		userService = new UserServiceimpl(test);
		userService.deleteUser(-1);
	}
	
	@Test
	public void testDeleteUserNotFound() {
		
		UserService userService;
		Boolean result;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(null);
		doNothing().when(test).delete(anyInt());
		
		userService = new UserServiceimpl(test);
		
		try {
			
			result = userService.deleteUser(1);
			assertEquals(result,false);
			
		} catch (InvalidUserException e) {
			fail("unexpected Exception raised by deleteUser");
		}
	}
	
	@Test
	public void testDeleteUser() {
		
		UserService userService;
		Boolean result;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
		doNothing().when(test).delete(anyInt());
		
		userService = new UserServiceimpl(test);
		
		try {
		
			result = userService.deleteUser(1);
			assertEquals(result,true);
		
		} catch (InvalidUserException e) {
			fail("unexpected Exception raised by deleteUser");
		}
		
	}

	
	@Test(expected = InvalidUserException.class)
	public void testDecreaseUserReputationByIdNullValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
		when(test.save(any(User.class))).thenReturn(new User());
			
		userService = new UserServiceimpl(test);
		userService.decreaseUserReputation(null);

	}

	@Test(expected = InvalidUserException.class)
	public void testDecreaseUserReputationByIdNegativeValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		userService.decreaseUserReputation(-1);
	}
	
	@Test(expected = InvalidUserException.class)
	public void testDecreaseUserReputationUserNotFound() throws InvalidUserException {
		
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(null);
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		userService.decreaseUserReputation(anyInt());
	}
	
	@Test
	public void testDecreaseUserReputationMaxValue() {
		
		UserService userService;
		Integer result;
		User u = new User();
		u.setReputation(-5);
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(u);
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		
		try {

			result = userService.decreaseUserReputation(anyInt());
			assert(result == -5);
		
		} catch (InvalidUserException e) {
			fail("unexpected Exception raised by decreaseUserReputation");
		}
		
	}
	
	@Test
	public void testDecreaseUserReputation() throws InvalidUserException {
		
		UserService userService;
		Integer result;
		User u = new User();
		u.setReputation(-4);
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(u);
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		
		try {
		
			result = userService.decreaseUserReputation(anyInt());
			assert(result == -5);
		
		} catch (InvalidUserException e) {
			fail("unexpected Exception raised by decreaseUserReputation");
		}
			
	}
	
	
	@Test(expected = InvalidUserException.class)
	public void testIncreaseUserReputationByIdNullValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
		when(test.save(any(User.class))).thenReturn(new User());
			
		userService = new UserServiceimpl(test);
		userService.increaseUserReputation(null);

	}

	@Test(expected = InvalidUserException.class)
	public void testIncreaseUserReputationByIdNegativeValue() throws InvalidUserException {
			
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(new User());
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		userService.increaseUserReputation(-1);
	}
	
	@Test(expected = InvalidUserException.class)
	public void testIncreaseUserReputationUserNotFound() throws InvalidUserException {
		
		UserService userService;
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(null);
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		userService.increaseUserReputation(anyInt());
	}
	
	@Test
	public void testIncreaseUserReputationMaxValue() throws InvalidUserException {
		
		UserService userService;
		Integer result;
		User u = new User();
		u.setReputation(5);
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(u);
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		
		try {
			
			result = userService.increaseUserReputation(anyInt());
			assert(result == 5);
		
		} catch (InvalidUserException e) {
			fail("unexpected Exception raised by increaseUserReputation");
		}
	}
	
	@Test
	public void testIncreaseUserReputation() throws InvalidUserException {
		
		UserService userService;
		Integer result;
		User u = new User();
		u.setReputation(4);
			
		UserRepository test = mock(UserRepository.class);
		when(test.findOne(anyInt())).thenReturn(u);
		when(test.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(test);
		
		try {
			
			result = userService.increaseUserReputation(anyInt());
			assert(result == 5);
		
		} catch (InvalidUserException e) {
			fail("unexpected Exception raised by increaseUserReputation");
		}
	}

	
	@Test(expected = InvalidLoginDataException.class)
	public void testLoginNullCredentials() throws InvalidLoginDataException {
		
		
		UserService userService;
		
		UserRepository testU = mock(UserRepository.class);
		
		userService = new UserServiceimpl(testU);
		
		userService.login(null);
	
	}
	
	@Test(expected = InvalidLoginDataException.class)
	public void testLoginNotFound() throws InvalidLoginDataException {
		
		
		UserService userService;
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findByPasswordAndEmail(anyString(), anyString())).thenReturn(new ArrayList<User>());
		
		userService = new UserServiceimpl(testU);
		
		IdPw credentials = new IdPw("username","password");
		
		userService.login(credentials);
	
	}
	
	@Test
	public void testLogin() {
		
		UserService userService;
		User u = new User();
		
		u.setUserId(1);
		u.setEmail("email");
		u.setReputation(5);
		u.setUserName("username");
		u.setAdmin(false);
		
		
		List<User> list = new ArrayList<User>();
		list.add(u);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findByPasswordAndEmail(anyString(), anyString())).thenReturn(list);
		
		userService = new UserServiceimpl(testU);
		
		IdPw credentials = new IdPw("username","password");
		
		try {
		
			LoginDto result = userService.login(credentials);
			assertEquals(result.getUserId(),u.getUserId());
			assertEquals(result.getEmail(),u.getEmail());
			assertEquals(result.getReputation(),u.getReputation());
			assertEquals(result.getUserName(),u.getUserName());
			assertFalse(result.getAdmin());
		
		} catch (InvalidLoginDataException e) {
			fail("unexpected Exception raised by login");
		}
		
	}
	
	@Test
	public void testSaveUserOverlapping() {
		
		UserService userService;
		User u = new User();
		
		u.setUserId(1);
		
		List<User> list = new ArrayList<User>();
		list.add(u);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findByEmail(anyString())).thenReturn(list);
		
		userService = new UserServiceimpl(testU);
		
		UserDto uDto = new UserDto();
				
		assertEquals(userService.saveUser(uDto).getEmail(),null);
				
	}
	
	@Test
	public void testSaveUser() {
		
		UserService userService;
	
		UserRepository testU = mock(UserRepository.class);
		when(testU.findByEmail(anyString())).thenReturn(new ArrayList<User>());
		when(testU.save(any(User.class))).thenReturn(new User());
		
		userService = new UserServiceimpl(testU);
		
		assertEquals(userService.saveUser(new UserDto()).getEmail(),null);			
	}

	
}
