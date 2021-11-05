package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EZGasApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	//UserEntity
	
	@Test
	public void testUserGetterAndSetter() {
		
		User u = new User();
		
		u.setAdmin(true);
		u.setEmail("email");
		u.setPassword("password");
		u.setReputation(1);
		u.setUserId(1);
		u.setUserName("username");
	
		assertTrue(u.getAdmin());
		assertEquals(u.getEmail(),"email");
		assertEquals(u.getPassword(),"password");
		assert(u.getReputation() == 1);
		assert(u.getUserId() == 1);
		assertEquals(u.getUserName(), "username");

	}
	
	@Test
	public void testUserConstructor() {
		
		User u = new User("username","password","email",1);

		assertEquals(u.getEmail(),"email");
		assertEquals(u.getPassword(),"password");
		assertEquals(u.getUserName(), "username");
		assert(u.getReputation() == 1);

	}
	
	//GasStationEntity
	
	@Test
	public void testGasStationGetterAndSetter() {
		
		GasStation g = new GasStation();
		User u = new User();
		u.setUserId(1);
		
		g.setCarSharing("carsharing");
		g.setDieselPrice(1);
		g.setGasPrice(1);
		g.setGasStationAddress("address");
		g.setGasStationId(1);
		g.setGasStationName("name");
		g.setHasDiesel(true);
		g.setHasGas(true);
		g.setHasMethane(false);
		g.setHasSuper(true);
		g.setHasSuperPlus(false);
		g.setLat(1);
		g.setLon(1);
		g.setMethanePrice(1);
		g.setReportDependability(1);
		g.setReportTimestamp("timestamp");
		g.setReportUser(1);
		g.setSuperPlusPrice(1);
		g.setSuperPrice(1);
		g.setUser(u);
	
		assertEquals(g.getCarSharing(),"carsharing");
		assert(g.getDieselPrice() == 1);
		assert(g.getGasPrice() == 1);
		assertEquals(g.getGasStationAddress(),"address");
		assert(g.getGasStationId() == 1);
		assertEquals(g.getGasStationName(),"name");
		assertTrue(g.getHasDiesel());
		assertTrue(g.getHasGas());
		assertFalse(g.getHasMethane());
		assertTrue(g.getHasSuper());
		assertFalse(g.getHasSuperPlus());
		assert(g.getLat() == 1);
		assert(g.getLon() == 1);
		assert(g.getMethanePrice() == 1);
		assert(g.getReportDependability() == 1);
		assertEquals(g.getReportTimestamp(), "timestamp");
		assert(g.getReportUser() == 1);
		assert(g.getSuperPlusPrice() == 1);
		assert(g.getSuperPrice() == 1);
		assert(g.getUser().getUserId() == 1);
	}
	
	@Test
	public void testGasStationConstructor() {
		
		GasStation g = new GasStation("name","address",true,true,false,true,false,
									"carsharing",1,1,1,1,1,1,1,1,"timestamp",1);
		
		assertEquals(g.getCarSharing(),"carsharing");
		assert(g.getDieselPrice() == 1);
		assert(g.getGasPrice() == 1);
		assertEquals(g.getGasStationAddress(),"address");
		assertEquals(g.getGasStationName(),"name");
		assertTrue(g.getHasDiesel());
		assertTrue(g.getHasGas());
		assertFalse(g.getHasMethane());
		assertTrue(g.getHasSuper());
		assertFalse(g.getHasSuperPlus());
		assert(g.getLat() == 1);
		assert(g.getLon() == 1);
		assert(g.getMethanePrice() == 1);
		assert(g.getReportDependability() == 1);
		assertEquals(g.getReportTimestamp(), "timestamp");
		assert(g.getReportUser() == 1);
		assert(g.getSuperPlusPrice() == 1);
		assert(g.getSuperPrice() == 1);
	}
	

}
