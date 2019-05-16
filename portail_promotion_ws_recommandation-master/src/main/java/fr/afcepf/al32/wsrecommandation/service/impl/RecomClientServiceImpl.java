package fr.afcepf.al32.wsrecommandation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;
import fr.afcepf.al32.wsrecommandation.dao.IRecommandationClientDao;
import fr.afcepf.al32.wsrecommandation.service.IRecomClientService;

@Service
public class RecomClientServiceImpl implements IRecomClientService {

	@Autowired
	private IRecommandationClientDao recommendationClientDao;

	@Override
	public List<PromotionTemplateResultDto> rechercherRecommandationsPromotionsByCategory(Double sourceLong,
			Double sourceLat, String category) {
		return recommendationClientDao.findDixPromotionsPrefereesByCategorie(new Point(sourceLong, sourceLat),
				category);
	}

}
