package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDtoTest {

	@Test
	public void contextLoads() {
	}
		
	@Test
	public void testUserDtoGetterAndSetter() {
		
		UserDto uDto = new UserDto();
		
		uDto.setAdmin(true);
		uDto.setEmail("email");
		uDto.setPassword("password");
		uDto.setReputation(1);
		uDto.setUserId(1);
		uDto.setUserName("username");
	
		assertTrue(uDto.getAdmin());
		assertEquals(uDto.getEmail(),"email");
		assertEquals(uDto.getPassword(),"password");
		assert(uDto.getReputation() == 1);
		assert(uDto.getUserId() == 1);
		assertEquals(uDto.getUserName(), "username");

	}
	
	@Test
	public void testUserDtoConstructor() {
		
		UserDto uDto = new UserDto(1,"username","password","email",1);

		assertEquals(uDto.getEmail(),"email");
		assertEquals(uDto.getPassword(),"password");
		assertEquals(uDto.getUserName(), "username");
		assert(uDto.getReputation() == 1);
		assertFalse(uDto.getAdmin());
	}

	@Test
	public void testUserDtoConstructorWithAdmin() {
		
		UserDto uDto = new UserDto(1,"username","password","email",1,true);

		assertEquals(uDto.getEmail(),"email");
		assertEquals(uDto.getPassword(),"password");
		assertEquals(uDto.getUserName(), "username");
		assert(uDto.getReputation() == 1);
		assertTrue(uDto.getAdmin());
	}
	
}
