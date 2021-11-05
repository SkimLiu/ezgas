package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.polito.ezgas.entity.GasStation;

public interface GasStationRepository extends JpaRepository<GasStation, Integer> {
	List<GasStation> findByHasDieselTrueOrderByDieselPrice();
	List<GasStation> findByHasGasTrueOrderByGasPrice();
	List<GasStation> findByHasMethaneTrueOrderByMethanePrice();
	List<GasStation> findByHasSuperTrueOrderBySuperPrice();
	List<GasStation> findByHasSuperPlusTrueOrderBySuperPlusPrice();
	List<GasStation> findByCarSharingOrderByCarSharing(String carSharing);
}

