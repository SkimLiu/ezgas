package it.polito.ezgas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.polito.ezgas.entity.PriceReport;

public interface PriceReportRepository extends JpaRepository<PriceReport, Integer> {

}
