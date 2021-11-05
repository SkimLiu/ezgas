package it.polito.ezgas.converter;

import it.polito.ezgas.dto.PriceReportDto;
import it.polito.ezgas.entity.PriceReport;

public class PriceReportConverter {
	
	public PriceReportDto toPriceReportDto (PriceReport priceReport) {
		
		PriceReportDto result = new PriceReportDto(priceReport.getPriceReportId(), priceReport.getUser(), priceReport.getDieselPrice(),
				priceReport.getSuperPrice(), priceReport.getSuperPlusPrice(), priceReport.getGasPrice());
		
		return result;
	}
	
	public PriceReport toPriceReport (PriceReportDto priceReportDto) {
		
		PriceReport result = new PriceReport(priceReportDto.getUser(), priceReportDto.getDieselPrice(),
				priceReportDto.getSuperPrice(), priceReportDto.getSuperPlusPrice(), priceReportDto.getGasPrice());
		
		return result;
	}	
	

}
