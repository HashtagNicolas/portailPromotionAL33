package fr.afcepf.al32.groupe2.service.decorator;

import java.time.Duration;

import fr.afcepf.al32.groupe2.entity.Promotion;
import fr.afcepf.al32.groupe2.service.factory.DiscountFactory;
import fr.afcepf.al32.groupe2.service.factory.PackFactory;
import fr.afcepf.al32.groupe2.service.factory.PercentTypeFactory;



public class ProductWPromotionApplyDecorator implements IProductWPromotionApplyDecorator {
	
	private static ProductWPromotionApplyDecorator promotionFactory;
		
	private String name;
	private String description;
	private Duration limitTimePromotion;
	private Duration limitTimeTakePromotion;
	private Double quantityInitAvailable;
	private Boolean isCumulative;
	
	private String promotionType;
	
	private Integer numberOffered;
	private Integer numberPurchased;
	private Double minPurchaseAmountDiscount;
	private Double discountValue;
	private Double percentValue;
	private Double minPurchaseAmountPercent;
	
	public static ProductWPromotionApplyDecorator getPromotionType() {
		if(promotionFactory == null) {
			promotionFactory = new ProductWPromotionApplyDecorator();
		}
		
		return promotionFactory;
	}
	
	public Promotion fabrique() {
		Promotion promotion = new Promotion();
		
		promotion.setName(name);
		promotion.setDescription(description);
		promotion.setLimitTimePromotion(limitTimePromotion);
		promotion.setLimitTimeTakePromotion(limitTimeTakePromotion);
		promotion.setQuantityInitAvailable(quantityInitAvailable);
		promotion.setIsCumulative(isCumulative);
		switch (promotionType) {
		case "Pack":
			promotion.setPromotionType(PackFactory.getPackType().fabrique());
			PackFactory.getPackType().setNumberOffered(numberOffered);
			PackFactory.getPackType().setNumberPurchased(numberPurchased);
			break;
		case "Discount":
			promotion.setPromotionType(DiscountFactory.getDiscountType().fabrique());
			DiscountFactory.getDiscountType().setDiscountValue(discountValue);
			DiscountFactory.getDiscountType().setMinPurchaseAmount(minPurchaseAmountDiscount);
			break;
		case "PercentType":
			promotion.setPromotionType(PercentTypeFactory.getPercentTypeType().fabrique());
			PercentTypeFactory.getPercentTypeType().setPercentValue(percentValue);
			PercentTypeFactory.getPercentTypeType().setMinPurchaseAmount(minPurchaseAmountPercent);
			break;
		default:
			break;
		}
		return promotion;	
	}

	public ProductWPromotionApplyDecorator createTypePack() {
		this.promotionType = "Pack";
		return this;
	}
	
	public ProductWPromotionApplyDecorator createTypeDiscount() {
		this.promotionType = "Discount";
		return this;
	}
	
	public ProductWPromotionApplyDecorator createTypePercentType() {
		this.promotionType = "PercentType";
		return this;
	}
	
	@Override
	public IProductWPromotionApplyDecorator setNamePromotion(String name) {
		this.name = name;
		return this;
	}

	@Override
	public IProductWPromotionApplyDecorator setDescriptionPromotion(String description) {
		this.description = description;
		return this;
	}

	@Override
	public IProductWPromotionApplyDecorator setLimitTimePromotion(Duration limitTimePromotion) {
		this.limitTimePromotion = limitTimePromotion;
		return this;
	}

	@Override
	public IProductWPromotionApplyDecorator setLimitTimeTakePromotion(Duration limitTimeTakePromotion) {
		this.limitTimeTakePromotion = limitTimeTakePromotion;
		return this;
	}

	@Override
	public IProductWPromotionApplyDecorator setQuantityInitAvailable(Double quantityInitAvailable) {
		this.quantityInitAvailable = quantityInitAvailable;
		return this;
	}

	@Override
	public IProductWPromotionApplyDecorator setIsCumulative(Boolean isCumulative) {
		this.isCumulative = isCumulative;
		return this;
	}

	@Override
	public Long createProduct(Long id) {
		return id;
		
		
	}

}
