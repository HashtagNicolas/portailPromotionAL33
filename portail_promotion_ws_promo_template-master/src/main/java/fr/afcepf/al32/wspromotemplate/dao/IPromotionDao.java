package fr.afcepf.al32.wspromotemplate.dao;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;
import org.springframework.data.geo.Point;

import java.util.List;

public interface IPromotionDao {
    List<PromotionTemplateResultDto> getTopFivePromotionByShopKeeper(Point sourcePoint, String[] categories);
}
