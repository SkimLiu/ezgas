package it.polito.ezgas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.IdPw;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IdPwTest {
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testIdPwGetterSetter() {
		
		IdPw idpw = new IdPw();
		
		idpw.setPw("password");
		idpw.setUser("username");
		
		assertEquals(idpw.getPw(), "password");
		assertEquals(idpw.getUser(), "username");

	}
	
	@Test
	public void testIdPwConstructor() {
		
		IdPw idpw = new IdPw("username", "password");
		
		assertEquals(idpw.getPw(), "password");
		assertEquals(idpw.getUser(), "username");

	}

}
