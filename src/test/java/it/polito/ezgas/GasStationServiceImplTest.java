package it.polito.ezgas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GasStationServiceImplTest {

	
	@Test
	public void testIsCoordinateValidWrongValues() {

		assertFalse(GasStationServiceimpl.isCoordinateValid(91, 180));
		
		assertFalse(GasStationServiceimpl.isCoordinateValid(-92, 180));
		
		assertFalse(GasStationServiceimpl.isCoordinateValid(90, 181));
		
		assertFalse(GasStationServiceimpl.isCoordinateValid(90, -182));
		
	}
	
	@Test
	public void testIsCoordinateValidRightValues() {

		//boundaries condition
		assertTrue(GasStationServiceimpl.isCoordinateValid(90, 180));
		
		assertTrue(GasStationServiceimpl.isCoordinateValid(89, 179));
	
		assertTrue(GasStationServiceimpl.isCoordinateValid(-90, -180));
		
		assertTrue(GasStationServiceimpl.isCoordinateValid(-89, -179));
		
	}

	
	@Test
	public void testIsPriceValidWrongValues() {

		GasStation g = new GasStation();
		g.setDieselPrice(3);
		g.setHasDiesel(true);
		
		g.setHasGas(true);
		g.setGasPrice(-2);
		
		assertFalse(GasStationServiceimpl.isPriceValid(g));
		
	}
	
	@Test
	public void testIsPriceValidRightValues() {

		GasStation g = new GasStation();
		g.setDieselPrice(1);
		g.setHasDiesel(true);
		
		g.setHasGas(true);
		g.setGasPrice(2);
		
		g.setHasMethane(false);
		g.setMethanePrice(-1);
		
		g.setHasSuper(true);
		g.setSuperPrice(1);
		
		g.setHasSuperPlus(true);
		g.setSuperPlusPrice(2);
		
		assertTrue(GasStationServiceimpl.isPriceValid(g));
	}
	
	@Test
	public void testGetGasStationZero() {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(new ArrayList<GasStation>());
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		ArrayList<GasStationDto> gsarray = (ArrayList<GasStationDto>) gasStationService.getAllGasStations();
		
		assert(gsarray.size() == 0);
	}
	
	@Test
	public void testGetGasStationOne() {
		
		GasStationService gasStationService;
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		list.add(new GasStation());
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		ArrayList<GasStationDto> gsarray = (ArrayList<GasStationDto>) gasStationService.getAllGasStations();
		
		assert(gsarray.size() == 1);
	}

	@Test
	public void testGetGasStationTwo() {
		
		GasStationService gasStationService;
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		list.add(new GasStation());
		list.add(new GasStation());
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		ArrayList<GasStationDto> gsarray = (ArrayList<GasStationDto>) gasStationService.getAllGasStations();
		
		assert(gsarray.size() == 2);
	}
	
	@Test
	public void testGetByProximityWrongCoordinates() {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(new ArrayList<GasStation>());
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
			gasStationService.getGasStationsByProximity(-90.5, 180);
		} catch (GPSDataException e) {
			assertEquals(e.getMessage(),"Invalid GPS data");
		}

		try {
			gasStationService.getGasStationsByProximity(70, 181);
		} catch (GPSDataException e) {
			assertEquals(e.getMessage(),"Invalid GPS data");
		}		
		
		try {
			gasStationService.getGasStationsByProximity(91, 180);
		} catch (GPSDataException e) {
			assertEquals(e.getMessage(),"Invalid GPS data");
		}
		
		try {
			gasStationService.getGasStationsByProximity(90, -181);
		} catch (GPSDataException e) {
			assertEquals(e.getMessage(),"Invalid GPS data");
		}
	}
	
	
	@Test
	public void testGetByProximityRightCoordinatesNotNullList() throws GPSDataException {
		
		GasStationService gasStationService;
		GasStation g1 = new GasStation();
		g1.setReportTimestamp(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)).toString());
		g1.setLat(90);
		g1.setLon(180);
		g1.setReportUser(1);
		
		User u = new User();
		u.setReputation(2);
		
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		list.add(g1);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(u);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
		
			List<GasStationDto> result = gasStationService.getGasStationsByProximity(90, 180);
			assert(result.get(0).getReportDependability() == 70.71428571428572);
			
		} catch(GPSDataException e) {
			fail("unexpected Exception raised in getByProximity");
		}
		
	}

	
	@Test(expected = InvalidGasStationException.class)
	public void testGetGasStationByIdNullValue() throws InvalidGasStationException {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(new GasStation());
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.getGasStationById(null);

	}
	
	@Test(expected = InvalidGasStationException.class)
	public void testGetGasStationByIdNegativeValue() throws InvalidGasStationException {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(new GasStation());
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.getGasStationById(-1);
	}
	
	@Test
	public void testGetGasStationByIdNotFound() {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(null);
		
		UserRepository testU = mock(UserRepository.class);
		try {
			
			gasStationService = new GasStationServiceimpl(testG,testU);
			assertEquals(gasStationService.getGasStationById(1),null);
			
		} catch(InvalidGasStationException e) {
			fail("unexpected Exception raised in getGasStationById");
		}
	
	}
	
	@Test
	public void testGetGasStationById() {
		
		GasStationService gasStationService;
		GasStation g = new GasStation();
		g.setGasStationId(1);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(g);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
				
		try {
			
			GasStationDto gDto = gasStationService.getGasStationById(anyInt());
			assertEquals(g.getGasStationId(), gDto.getGasStationId());
			
		} catch(InvalidGasStationException e) {
			fail("unexpected Exception raised in getGasStationById");
		}
	}
	
	@Test(expected = GPSDataException.class)
	public void testSaveGasStationWrongCoordinates() throws GPSDataException, PriceException {
		
		GasStationService gasStationService;
		GasStationDto gDto = new GasStationDto();
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.save(any(GasStation.class))).thenReturn(new GasStation());
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gDto.setLat(-90.5);
		gDto.setLon(180.0);
		gasStationService.saveGasStation(gDto);
	}
	
	@Test
	public void testSaveGasStationRightCoordinates() {
		
		GasStationService gasStationService;
		GasStation g = new GasStation();
		g.setGasStationId(1);
		
		GasStationDto gDto = new GasStationDto();
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.save(any(GasStation.class))).thenReturn(g);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
		
			gDto = gasStationService.saveGasStation(gDto);
			assertEquals(gDto.getGasStationId(),g.getGasStationId());
		
		} catch (PriceException | GPSDataException e) {
			fail("unexpected Exception raised in saveGasStation");
		}
		
	}
	
	
	@Test(expected = InvalidGasStationException.class)
	public void testDeleteGasStationNullValue() throws InvalidGasStationException {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.deleteGasStation(null);

	}
	
	@Test(expected = InvalidGasStationException.class)
	public void testDeleteGasStationNegativeValue() throws InvalidGasStationException {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(new GasStation());
		doNothing().when(testG).delete(any(GasStation.class));
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.deleteGasStation(-1);
	}
	
	@Test
	public void testDeleteGasStationNotFound() {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(null);
		doNothing().when(testG).delete(any(GasStation.class));
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
		
			assertFalse(gasStationService.deleteGasStation(anyInt()));
		
		} catch(InvalidGasStationException e) {
			fail("unexpected Exception raised in deleteGasStation");
		}
	}
	
	@Test
	public void testDeleteGasStation() {
		
		GasStationService gasStationService;
		GasStation g = new GasStation();
		g.setGasStationId(1);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(g);
		doNothing().when(testG).delete(any(GasStation.class));
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
			
			assertTrue(gasStationService.deleteGasStation(anyInt()));
		
		} catch(InvalidGasStationException e) {
			fail("unexpected Exception raised in deleteGasStation");
		}
		
	}
	

	
	@Test
	public void testGetGasStationByGasolineType() {
		
		GasStationService gasStationService;
		
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		list.add(new GasStation());
		list.add(new GasStation());
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findByHasDieselTrueOrderByDieselPrice()).thenReturn(list);
		when(testG.findByHasGasTrueOrderByGasPrice()).thenReturn(list);
		when(testG.findByHasMethaneTrueOrderByMethanePrice()).thenReturn(list);
		when(testG.findByHasSuperPlusTrueOrderBySuperPlusPrice()).thenReturn(list);
		when(testG.findByHasSuperTrueOrderBySuperPrice()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
			
			assertEquals(gasStationService.getGasStationsByGasolineType("super").size(), 2);
			assertEquals(gasStationService.getGasStationsByGasolineType("diesel").size(), 2);
			assertEquals(gasStationService.getGasStationsByGasolineType("gas").size(), 2);
			assertEquals(gasStationService.getGasStationsByGasolineType("superPlus").size(), 2);
			assertEquals(gasStationService.getGasStationsByGasolineType("methane").size(), 2);
		
		} catch(InvalidGasTypeException e) {
			fail("unexpected Exception raised in getGasStationById");
		}
		
	}
	
	@Test(expected = InvalidGasTypeException.class)
	public void testGetGasStationByGasolineTypeWrongType() throws InvalidGasTypeException {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.getGasStationsByGasolineType("super+");

	}
	
	@Test(expected = InvalidGasTypeException.class)
	public void testGetGasStationByGasolineTypeNullType() throws InvalidGasTypeException {
		
		GasStationService gasStationService;
		
		GasStationRepository testG = mock(GasStationRepository.class);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.getGasStationsByGasolineType(null);

	}
	
	@Test
	public void testGetGasStationByCarSharing() {
		
		GasStationService gasStationService;
		
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		GasStation g = new GasStation();
		g.setReportTimestamp(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)).toString());
		
		list.add(g);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findByCarSharingOrderByCarSharing(anyString())).thenReturn(list);
		
		User u = new User();
		u.setReputation(2);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(u);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		List<GasStationDto> result = gasStationService.getGasStationByCarSharing(anyString());
		
		assert(result.get(0).getReportDependability() == 70.71428571428572);
	}

	
	@Test(expected = GPSDataException.class)
	public void testGetGasStationsWithCoordinatesWrong() throws GPSDataException, InvalidGasTypeException {
		
		GasStationService gasStationService;
		
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		list.add(new GasStation());
		list.add(new GasStation());
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findByHasDieselTrueOrderByDieselPrice()).thenReturn(list);
		when(testG.findByHasGasTrueOrderByGasPrice()).thenReturn(list);
		when(testG.findByHasMethaneTrueOrderByMethanePrice()).thenReturn(list);
		when(testG.findByHasSuperPlusTrueOrderBySuperPlusPrice()).thenReturn(list);
		when(testG.findByHasSuperTrueOrderBySuperPrice()).thenReturn(list);
		when(testG.findByCarSharingOrderByCarSharing(anyString())).thenReturn(list);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.getGasStationsWithCoordinates(-90.5, 180, "", "");
	}
	
	@Test
	public void testGetGasStationsWithCoordinates() {
		
		GasStationService gasStationService;
		
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		GasStation g = new GasStation();
		g.setLat(90);
		g.setLon(180);
		
		list.add(g);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findByHasDieselTrueOrderByDieselPrice()).thenReturn(list);
		when(testG.findByHasGasTrueOrderByGasPrice()).thenReturn(list);
		when(testG.findByHasMethaneTrueOrderByMethanePrice()).thenReturn(list);
		when(testG.findByHasSuperPlusTrueOrderBySuperPlusPrice()).thenReturn(list);
		when(testG.findByHasSuperTrueOrderBySuperPrice()).thenReturn(list);
		when(testG.findByCarSharingOrderByCarSharing(anyString())).thenReturn(list);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
		
			assertEquals(gasStationService.getGasStationsWithCoordinates(90, 180, null, null).size(),1);
		
		} catch(GPSDataException | InvalidGasTypeException e) {
			fail("unexpected Exception raised in getGasStationWithCoordinates");
		}
	}

	
	@Test
	public void testGetGasStationsWithoutCoordinates() {
		
		GasStationService gasStationService;
		
		ArrayList<GasStation> list = new ArrayList<GasStation>();
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findByHasDieselTrueOrderByDieselPrice()).thenReturn(list);
		when(testG.findByHasGasTrueOrderByGasPrice()).thenReturn(list);
		when(testG.findByHasMethaneTrueOrderByMethanePrice()).thenReturn(list);
		when(testG.findByHasSuperPlusTrueOrderBySuperPlusPrice()).thenReturn(list);
		when(testG.findByHasSuperTrueOrderBySuperPrice()).thenReturn(list);
		when(testG.findByCarSharingOrderByCarSharing(anyString())).thenReturn(list);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
		
			assertEquals(gasStationService.getGasStationsWithoutCoordinates(null, null).size(),0);
			assertEquals(gasStationService.getGasStationsWithoutCoordinates("super", "carSharing").size(),0);
			assertEquals(gasStationService.getGasStationsWithoutCoordinates("super", null).size(),0);
			assertEquals(gasStationService.getGasStationsWithoutCoordinates(null, anyString()).size(),0);
		
		} catch(InvalidGasTypeException e) {
			fail("unexpected Exception raised in getGasStationWithoutCoordinates");
		}
	}
		
	
	@Test(expected = InvalidGasStationException.class)
	public void testSetReportNegativeGasStationId() throws InvalidGasStationException, PriceException, InvalidUserException {
		
		GasStationService gasStationService;
				
		GasStationRepository testG = mock(GasStationRepository.class);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.setReport(-1, 1, 1, 1, 1, 1, anyInt());
	}
		
	@Test(expected = InvalidGasStationException.class)
	public void testSetReportNullGasStationId() throws InvalidGasStationException, PriceException, InvalidUserException {
		
		GasStationService gasStationService;
				
		GasStationRepository testG = mock(GasStationRepository.class);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.setReport(null, 1, 1, 1, 1, 1, 1);
	}
	
	@Test(expected = InvalidGasStationException.class)
	public void testSetReportGasStationNotFound() throws InvalidGasStationException, PriceException, InvalidUserException {
		
		GasStationService gasStationService;
				
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(null);
		
		UserRepository testU = mock(UserRepository.class);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.setReport(1, 1, 1, 1, 1, 1, 1);
	}
	
	@Test(expected = InvalidUserException.class)
	public void testSetReportNullUserId() throws InvalidUserException, InvalidGasStationException, PriceException {
		
		GasStationService gasStationService;
				
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(new GasStation());
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(new User());
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.setReport(1, 1, 1, 1, 1, 1, null);
	}
	
	@Test(expected = InvalidUserException.class)
	public void testSetReportUserNotFound() throws InvalidGasStationException, PriceException, InvalidUserException {
		
		GasStationService gasStationService;
				
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(new GasStation());
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(null);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.setReport(1, 1, 1, 1, 1, 1, 1);
	}
		
	@Test(expected = PriceException.class)
	public void testSetReportPriceError() throws InvalidGasStationException, PriceException, InvalidUserException {
		
		GasStationService gasStationService;
		GasStation g = new GasStation();
		g.setHasDiesel(true);
				
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(g);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(new User());
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		gasStationService.setReport(1, -1, 1, 1, 1, 1, 1);
	}
	
	@Test
	public void testSetReport()  {
		
		GasStationService gasStationService;
		GasStation g = new GasStation();
		g.setHasDiesel(true);
		
		User u = new User();
		u.setReputation(2);
				
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findOne(anyInt())).thenReturn(g);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(u);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		try {
		
			gasStationService.setReport(1, 1, 1, 1, 1, 1, 1);
			assertTrue(true);
		
		} catch(InvalidGasStationException | PriceException | InvalidUserException e) {
			fail("unexpected Exception raised in setReport");
		}
	}
	
	
	@Test
	public void testDistanceMethod() {
		
		assertEquals((int)GasStationServiceimpl.distance(40.76, -71.984, 38.89, -77.032)/1000, 478);
		assertEquals((int)GasStationServiceimpl.distance(40.76, -71.032, 38.89, -77.032)/1000, 552);
		
	}
	
	@Test
	public void dependabilityTimeStamp2DaysBefore() {
		
		GasStationService gasStationService;
		
		GasStation g = new GasStation();
		g.setReportTimestamp(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)).toString());
		
		List<GasStation> list = new ArrayList<GasStation>();
		list.add(g);
		
		User u = new User();
		u.setReputation(2);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(u);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		List<GasStationDto> result = gasStationService.getAllGasStations();
		
		assert(result.get(0).getReportDependability() == 70.71428571428572);
		
	}
	
	@Test
	public void dependabilityNullTimeStamp() {
		
		GasStationService gasStationService;
		
		GasStation g = new GasStation();
		g.setReportTimestamp(null);
		
		List<GasStation> list = new ArrayList<GasStation>();
		list.add(g);
		
		User u = new User();
		u.setReputation(2);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(u);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		List<GasStationDto> result = gasStationService.getAllGasStations();
		
		assert(result.get(0).getReportDependability() == 0);
		
	}
	
	@Test
	public void dependabilityAfterOneWeek() {
		
		GasStationService gasStationService;
		
		GasStation g = new GasStation();
		g.setReportTimestamp(Timestamp.from(Instant.now().minus(8, ChronoUnit.DAYS)).toString());
		
		List<GasStation> list = new ArrayList<GasStation>();
		list.add(g);
		
		User u = new User();
		u.setReputation(2);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(u);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		List<GasStationDto> result = gasStationService.getAllGasStations();
		
		assert(result.get(0).getReportDependability() == 35);
		
	}
	
	@Test
	public void dependabilityUserNotFound() {
		
		GasStationService gasStationService;
		
		GasStation g = new GasStation();
		g.setReportTimestamp(Timestamp.from(Instant.now().minus(8, ChronoUnit.DAYS)).toString());
		
		List<GasStation> list = new ArrayList<GasStation>();
		list.add(g);
		
		GasStationRepository testG = mock(GasStationRepository.class);
		when(testG.findAll()).thenReturn(list);
		
		UserRepository testU = mock(UserRepository.class);
		when(testU.findOne(anyInt())).thenReturn(null);
		
		gasStationService = new GasStationServiceimpl(testG,testU);
		
		List<GasStationDto> result = gasStationService.getAllGasStations();
		
		assert(result.get(0).getReportDependability() == 0);
		
	}

}
