package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.LoginDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDtoTest {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testLoginDtoGetterSetter() {
		
		LoginDto loginDto = new LoginDto();
		
		loginDto.setAdmin(true);
		loginDto.setEmail("email");
		loginDto.setReputation(5);
		loginDto.setToken("token");
		loginDto.setUserId(1);
		loginDto.setUserName("username");
		
		
		assertTrue(loginDto.getAdmin());
		assertEquals(loginDto.getEmail(), "email");
		assert(loginDto.getReputation() == 5);
		assertEquals(loginDto.getToken(), "token");
		assert(loginDto.getUserId() == 1);
		assertEquals(loginDto.getUserName(), "username");
		
	}
	
	@Test
	public void testLoginDtoConstructor() {
		
		LoginDto loginDto = new LoginDto(1, "username", "token", "email", 5);

		assertEquals(loginDto.getEmail(), "email");
		assert(loginDto.getReputation() == 5);
		assertEquals(loginDto.getToken(), "token");
		assert(loginDto.getUserId() == 1);
		assertEquals(loginDto.getUserName(), "username");
		
	}
	
}
