package fr.afcepf.al32.wspromotemplate.service.itf;

//import fr.afcepf.al32.wspromotemplate.rest.SearchByShopperResponseDto;
//import fr.afcepf.al32.wspromotemplate.rest.ShopperResquestDto;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;

import java.util.List;

public interface IPromotionSearchService {
	List<PromotionTemplateResultDto> searchTopPromotionByShopKeeper(Double sourceLong, Double sourceLat, List<String> categories);


}
