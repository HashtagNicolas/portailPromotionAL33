package fr.afcepf.al32.groupe2.service;

import java.io.IOException;
import java.util.List;

import fr.afcepf.al32.groupe2.ws.dto.ResponseGeoApiDto;
import fr.afcepf.al32.groupe2.ws.dto.ResponseWsDto;


public interface IRechercheCommerceService {

	ResponseWsDto rechercherShopsByPerimetreEtDepart(String source, Integer perimetre) throws IOException;

	ResponseGeoApiDto verifierVraiAdresse(String source);

	ResponseWsDto shopToResponseWsDtoConverter(String source, Integer perimetre);
	
	List<Double> getCoordonnees(String source);
}
