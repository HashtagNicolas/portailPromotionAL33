package fr.afcepf.al32.groupe2.service.decorator;

import java.time.Duration;

public interface IProductWPromotionApplyDecorator extends IproductDecorator {

	IProductWPromotionApplyDecorator setNamePromotion(String name);
	IProductWPromotionApplyDecorator setDescriptionPromotion(String description);
	IProductWPromotionApplyDecorator setLimitTimePromotion(Duration limitTimePromotion);
	IProductWPromotionApplyDecorator setLimitTimeTakePromotion(Duration limitTimeTakePromotion);
	IProductWPromotionApplyDecorator setQuantityInitAvailable(Double quantityInitAvailable);
	IProductWPromotionApplyDecorator setIsCumulative(Boolean isCumulative);
}
