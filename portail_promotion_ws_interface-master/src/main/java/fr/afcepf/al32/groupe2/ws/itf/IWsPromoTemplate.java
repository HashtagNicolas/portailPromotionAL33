package fr.afcepf.al32.groupe2.ws.itf;

import java.util.List;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.TopPromotionTemplateResultDto;

public interface IWsPromoTemplate {
    TopPromotionTemplateResultDto searchTopTemplatePromoForShopKeeper(Double sourceLong, Double sourceLat, List<String> categories);
    TopPromotionTemplateResultDto searchByClientsFavoriteCategory(Double sourceLong, Double sourceLat, String category);
}
