package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationDtoTest {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testGasStationDtoGetterAndSetter() {
		
		GasStationDto gDto = new GasStationDto();
		UserDto uDto = new UserDto();
		
		uDto.setUserId(1);
		
		
		gDto.setCarSharing("carsharing");
		gDto.setDieselPrice(1);
		gDto.setGasPrice(1);
		gDto.setGasStationAddress("address");
		gDto.setGasStationId(1);
		gDto.setGasStationName("name");
		gDto.setHasDiesel(true);
		gDto.setHasGas(true);
		gDto.setHasMethane(false);
		gDto.setHasSuper(true);
		gDto.setHasSuperPlus(false);
		gDto.setLat(1.0);
		gDto.setLon(1.0);
		gDto.setMethanePrice(1);
		gDto.setReportDependability(1);
		gDto.setReportTimestamp("timestamp");
		gDto.setReportUser(1);
		gDto.setSuperPlusPrice(1);
		gDto.setSuperPrice(1);
		
		gDto.setUserDto(uDto);
	
		assertEquals(gDto.getCarSharing(),"carsharing");
		assert(gDto.getDieselPrice() == 1);
		assert(gDto.getGasPrice() == 1);
		assertEquals(gDto.getGasStationAddress(),"address");
		assert(gDto.getGasStationId() == 1);
		assertEquals(gDto.getGasStationName(),"name");
		assertTrue(gDto.getHasDiesel());
		assertTrue(gDto.getHasGas());
		assertFalse(gDto.getHasMethane());
		assertTrue(gDto.getHasSuper());
		assertFalse(gDto.getHasSuperPlus());
		assert(gDto.getLat() == 1);
		assert(gDto.getLon() == 1);
		assert(gDto.getMethanePrice() == 1);
		assert(gDto.getReportDependability() == 1);
		assertEquals(gDto.getReportTimestamp(), "timestamp");
		assert(gDto.getReportUser() == 1);
		assert(gDto.getSuperPlusPrice() == 1);
		assert(gDto.getSuperPrice() == 1);
		assert(gDto.getUserDto().getUserId() == 1);
	}
	
	@Test
	public void testGasStationDtoConstructor() {
		
		GasStationDto gDto = new GasStationDto(1,"name","address",true,true,false,true,false,
									"carsharing",1,1,1,1,1,1,1,1,"timestamp",1);
		
		assertEquals(gDto.getCarSharing(),"carsharing");
		assert(gDto.getDieselPrice() == 1);
		assert(gDto.getGasPrice() == 1);
		assertEquals(gDto.getGasStationAddress(),"address");
		assertEquals(gDto.getGasStationName(),"name");
		assertTrue(gDto.getHasDiesel());
		assertTrue(gDto.getHasGas());
		assertFalse(gDto.getHasMethane());
		assertTrue(gDto.getHasSuper());
		assertFalse(gDto.getHasSuperPlus());
		assert(gDto.getLat() == 1);
		assert(gDto.getLon() == 1);
		assert(gDto.getMethanePrice() == 1);
		assert(gDto.getReportDependability() == 1);
		assertEquals(gDto.getReportTimestamp(), "timestamp");
		assert(gDto.getReportUser() == 1);
		assert(gDto.getSuperPlusPrice() == 1);
		assert(gDto.getSuperPrice() == 1);
		assert(gDto.getGasStationId() == 1);
	}
	
}
