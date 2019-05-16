package fr.afcepf.al32.wspromotemplate.service.impl;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;
import fr.afcepf.al32.wspromotemplate.dao.IPromotionDao;
import fr.afcepf.al32.wspromotemplate.service.itf.IPromotionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PromotionSearchService implements IPromotionSearchService {

    @Autowired
    private IPromotionDao promotionDao;

    @Override
    public List<PromotionTemplateResultDto> searchTopPromotionByShopKeeper(Double sourceLong, Double sourceLat, List<String> categories) {
        String[] categoryArray = new String[categories.size()];
        categories.toArray(categoryArray);
        return promotionDao.getTopFivePromotionByShopKeeper(new Point(sourceLong, sourceLat), categoryArray);
    }
}
