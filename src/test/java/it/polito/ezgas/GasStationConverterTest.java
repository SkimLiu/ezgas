package it.polito.ezgas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationConverterTest {

	//this test is needed only to reach full coverage computed by Eclemma
	@Test
	public void coverage() {
		GasStationConverter gConverter = new GasStationConverter();
	}
	
	@Test
	public void testGasStationConverterToGasStationDto() {
		
		GasStation g = new GasStation("name", "address", true, true, false, false, true, 
							"carSharing", 40, 50, 1.5, 1.6, 1.7, 1.8, 1.9, 2, "timestamp", 0);
		g.setGasStationId(1);
		
		GasStationDto gDto = GasStationConverter.toGasStationDto(g);
		
		assertEquals(gDto.getGasStationName(),g.getGasStationName());
		assertEquals(gDto.getGasStationAddress(),g.getGasStationAddress());
		assertEquals(gDto.getHasDiesel(),g.getHasDiesel());
		assertEquals(gDto.getHasGas(),g.getHasGas());
		assertEquals(gDto.getHasMethane(),g.getHasMethane());
		assertEquals(gDto.getHasSuper(),g.getHasSuper());
		assertEquals(gDto.getHasSuperPlus(),g.getHasSuperPlus());
		assertEquals(gDto.getCarSharing(),g.getCarSharing());
		assert(gDto.getLat() == g.getLat());
		assert(gDto.getLon() == g.getLon());
		assert(gDto.getDieselPrice() == g.getDieselPrice());
		assert(gDto.getGasPrice() == g.getGasPrice());
		assert(gDto.getMethanePrice() == g.getMethanePrice());
		assert(gDto.getSuperPrice() == g.getSuperPrice());
		assert(gDto.getSuperPlusPrice() == g.getSuperPlusPrice());
		assertEquals(gDto.getReportUser(),g.getReportUser());
		assertEquals(gDto.getReportTimestamp(),g.getReportTimestamp());
		assert(gDto.getReportDependability() == g.getReportDependability());
		assertEquals(gDto.getGasStationId(), g.getGasStationId());
		
	}
			
	@Test
	public void testGasStationConverterToGasStation() {
		
		GasStationDto gDto = new GasStationDto(1,"name", "address", true, true, false, false, true, 
				"carSharing", 40, 50, 1.5, 1.6, 1.7, 1.8, 1.9, 2, "timestamp", 0);

		GasStation g = GasStationConverter.toGasStation(gDto);
		
		assertEquals(gDto.getGasStationName(),g.getGasStationName());
		assertEquals(gDto.getGasStationAddress(),g.getGasStationAddress());
		assertEquals(gDto.getHasDiesel(),g.getHasDiesel());
		assertEquals(gDto.getHasGas(),g.getHasGas());
		assertEquals(gDto.getHasMethane(),g.getHasMethane());
		assertEquals(gDto.getHasSuper(),g.getHasSuper());
		assertEquals(gDto.getHasSuperPlus(),g.getHasSuperPlus());
		assertEquals(gDto.getCarSharing(),g.getCarSharing());
		assert(gDto.getLat() == g.getLat());
		assert(gDto.getLon() == g.getLon());
		assert(gDto.getDieselPrice() == g.getDieselPrice());
		assert(gDto.getGasPrice() == g.getGasPrice());
		assert(gDto.getMethanePrice() == g.getMethanePrice());
		assert(gDto.getSuperPrice() == g.getSuperPrice());
		assert(gDto.getSuperPlusPrice() == g.getSuperPlusPrice());
		assertEquals(gDto.getReportUser(),g.getReportUser());
		assertEquals(gDto.getReportTimestamp(),g.getReportTimestamp());
		assert(gDto.getReportDependability() == g.getReportDependability());
		assertEquals(gDto.getGasStationId(), g.getGasStationId());
		
	}
	
}
