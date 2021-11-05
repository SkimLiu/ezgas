package it.polito.ezgas;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;
	
    @Autowired
    private UserRepository userRepository;
    
    @Before
    public void setUp(){
        
        User u1 = new User();
        u1.setEmail("email1");
        u1.setPassword("password1");
        
        User u2 = new User();
        u2.setEmail("email2");
        u2.setPassword("password2");
        
        testEntityManager.persist(u1);
        testEntityManager.persist(u2);
       
    }
    
    @Test
    public void testFindByPasswordAndEmail() {
    	
    	List<User> list = userRepository.findByPasswordAndEmail("password1", "email1");
    	
    	assertEquals(list.size(), 1);
    	assertEquals(list.get(0).getEmail(), "email1");
    	assertEquals(list.get(0).getPassword(), "password1");
    	
    }
    
    @Test
    public void testFindByEmail() {
    	
    	List<User> list = userRepository.findByEmail("email2");
    	
    	assertEquals(list.size(), 1);
    	assertEquals(list.get(0).getEmail(), "email2");
    	
    }

  
  
}