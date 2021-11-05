package it.polito.ezgas.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {
	private static final double MAX_PROXIMITY_DISTANCE = 1000;
	
	private GasStationRepository gasStationRepository;
	
	private UserRepository userRepository;
	
	public GasStationServiceimpl(GasStationRepository gasStationRepository, UserRepository userRepository) {
		this.gasStationRepository = gasStationRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		
		if (gasStationId == null)
			throw new InvalidGasStationException("Null gasStationId is not valid");
		
		if (gasStationId < 0)
			throw new InvalidGasStationException("Negative gasStationId is not valid");

		GasStation g = gasStationRepository.findOne(gasStationId);
		
		if (g == null)
			return null;

		g.setReportDependability(dependability(g));

		return GasStationConverter.toGasStationDto(g);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		GasStation g = GasStationConverter.toGasStation(gasStationDto);
		
		if (!isCoordinateValid(g.getLat(), g.getLon()))
			throw new GPSDataException("Invalid GPS data");

		//throw PriceException is missing
		
		return GasStationConverter.toGasStationDto(gasStationRepository.save(g));
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		return gasStationRepository.findAll()
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		
		if (gasStationId == null)
			throw new InvalidGasStationException("Null gasStationId is not valid");
		
		if (gasStationId < 0)
			throw new InvalidGasStationException("Negative gasStationId is not valid");
		
		GasStation g = gasStationRepository.findOne(gasStationId);
		
		if (g == null)
			return false;
		
		gasStationRepository.delete(g);
		
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolineType) throws InvalidGasTypeException {
		List<GasStation> gasStations;
		
		if (gasolineType == null)
			throw new InvalidGasTypeException("Null gasoline type is not valid");
		
		switch (gasolineType.toLowerCase()) {
			case "super":
				gasStations = gasStationRepository.findByHasSuperTrueOrderBySuperPrice();
				break;
			case "superplus":
				gasStations = gasStationRepository.findByHasSuperPlusTrueOrderBySuperPlusPrice();
				break;
			case "gas":
				gasStations = gasStationRepository.findByHasGasTrueOrderByGasPrice();
				break;
			case "diesel":
				gasStations = gasStationRepository.findByHasDieselTrueOrderByDieselPrice();
				break;
			case "methane":
				gasStations = gasStationRepository.findByHasMethaneTrueOrderByMethanePrice();
				break;
			default:
				throw new InvalidGasTypeException("No corresponding gas type for gasolineType=" + gasolineType);
		}
		
		return gasStations
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if (!isCoordinateValid(lat, lon))
			throw new GPSDataException("Invalid GPS data");
		
		return gasStationRepository.findAll()
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.filter(s -> distance(s.getLat(), s.getLon(), lat, lon) < MAX_PROXIMITY_DISTANCE)
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if (!isCoordinateValid(lat, lon))
			throw new GPSDataException("Invalid GPS data");
		
		return getGasStationsWithoutCoordinates(gasolinetype, carsharing).stream()
				.filter(s -> distance(s.getLat(), s.getLon(), lat, lon) < MAX_PROXIMITY_DISTANCE)
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		List<GasStationDto> gasStations;

		if (gasolinetype != null && !gasolinetype.equals("null")) {
			gasStations = getGasStationsByGasolineType(gasolinetype);
			if (carsharing != null && !carsharing.equals("null"))
				gasStations = gasStations.stream()
				.filter(s -> s.getCarSharing().equals(carsharing))
				.collect(Collectors.toList());
		} else if (carsharing != null && !carsharing.equals("null")) {
			gasStations = getGasStationByCarSharing(carsharing);
		} else {
			gasStations = getAllGasStations();
		}
		
		return gasStations.stream()
				.peek(s -> s.setReportDependability(dependability(GasStationConverter.toGasStation(s))))
				.collect(Collectors.toList());
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		
		if (gasStationId == null)
			throw new InvalidGasStationException("Null gasStationId is not valid");
		
		GasStation g = gasStationRepository.findOne(gasStationId);
		if (g == null)
			throw new InvalidGasStationException("No corresponding gas station for gasStationId=" + gasStationId);
		
		if (userId == null)
			throw new InvalidUserException("Null userId is not valid");
		
		User u = userRepository.findOne(userId);
		if (u == null)
			throw new InvalidUserException("No corresponding user for userId=" + userId);
		
		g.setDieselPrice(dieselPrice);
		g.setSuperPrice(superPrice);
		g.setSuperPlusPrice(superPlusPrice);
		g.setGasPrice(gasPrice);
		g.setMethanePrice(methanePrice);
		
		if (!isPriceValid(g))
			throw new PriceException("Undefined price for one or more gasoline types");

		g.setReportUser(userId);
		g.setReportTimestamp(Timestamp.from(Instant.now()).toString());
		g.setReportDependability(dependability(g));

		gasStationRepository.save(g);
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		return gasStationRepository.findByCarSharingOrderByCarSharing(carSharing)
				.stream()
				.peek(s -> s.setReportDependability(dependability(s)))
				.map(GasStationConverter::toGasStationDto)
				.collect(Collectors.toList());
	}

	/*
	 * 	Price validity criterion:
	 * 		- GasStation g.hasFuelType = true => fuelTypePrice >= 0
	 * 		- GasStation g.hasFuelType = false => any fuelTypePrice is valid
	 *
	 * 	@return true when all the prices of GasStation g are valid
	 */
	public static boolean isPriceValid(GasStation g) {
		return (!g.getHasDiesel() || !(g.getDieselPrice() < 0))
				&& (!g.getHasSuper() || !(g.getSuperPrice() < 0))
				&& (!g.getHasSuperPlus() || !(g.getSuperPlusPrice() < 0))
				&& (!g.getHasGas() || !(g.getGasPrice() < 0))
				&& (!g.getHasMethane() || !(g.getMethanePrice() < 0));
	}

	public static boolean isCoordinateValid(double lat, double lon) {
		return lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
	}

	public double dependability(GasStation gasStation) {
		String timestamp = gasStation.getReportTimestamp();

		if(timestamp == null)
			return 0;
		
		double ageDays = Timestamp.valueOf(timestamp)
				.toInstant()
				.until(Instant.now(), ChronoUnit.DAYS);

		double obsolescence = ageDays < 7 ? 1 - ageDays / 7 : 0;

		long reputation;
		User u = userRepository.findOne(gasStation.getReportUser());
		
		// if user associated to the report is not found, reputation is equal to the lowest value
		if(u == null)
			reputation = -5;
		else
			reputation = u.getReputation();

		return 5 * (reputation + 5) + 50 * obsolescence;
	}

	/**
	 * Calculate distance between two points in latitude and longitude.
	 * Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point
	 * @return Distance in Meters
	 */
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    
	    return R * c * 1000; // convert to meters
	}
}
