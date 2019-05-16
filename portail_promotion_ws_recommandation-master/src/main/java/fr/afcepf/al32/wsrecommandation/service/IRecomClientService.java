package fr.afcepf.al32.wsrecommandation.service;

import java.util.List;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;

public interface IRecomClientService {

	List<PromotionTemplateResultDto> rechercherRecommandationsPromotionsByCategory(Double sourceLong, Double sourceLat,
			String category);

}
