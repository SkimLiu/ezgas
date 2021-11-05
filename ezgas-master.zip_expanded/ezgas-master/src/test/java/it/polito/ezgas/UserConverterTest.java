package it.polito.ezgas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserConverterTest {
	
	//this test is needed only to reach full coverage computed by Eclemma
	@Test
	public void coverage() {
		UserConverter uConverter = new UserConverter();
	}
	
	@Test
	public void testUserConvertertoUserDto() {
		
		User u = new User("username", "password", "email", 5);
		u.setAdmin(false);
		u.setUserId(1);
	
		UserDto result = UserConverter.toUserDto(u);
		
		assertEquals(result.getAdmin(), false);
		assertEquals(result.getEmail(), "email");
		assertEquals(result.getPassword(),"password");
		assert(result.getUserId() == 1);
		assert(result.getReputation() == 5);
		assertEquals(result.getUserName(), "username");
	
	}
	
	@Test
	public void testUserConvertertoUser() {
		
		UserDto uDto = new UserDto(1,"username", "password", "email", 5,true);
	
		User result = UserConverter.toUser(uDto);
		
		assertEquals(result.getAdmin(), true);
		assertEquals(result.getEmail(), "email");
		assertEquals(result.getPassword(),"password");
		assert(result.getUserId() == 1);
		assert(result.getReputation() == 5);
		assertEquals(result.getUserName(), "username");	
	
	}

}
