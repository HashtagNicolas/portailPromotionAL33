package fr.afcepf.al32.wsrecommandation.dao;

import java.util.List;

import org.springframework.data.geo.Point;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;

public interface IRecommandationClientDao {

	List<PromotionTemplateResultDto> findDixPromotionsPrefereesByCategorie(Point sourcePoint, String category);

}
