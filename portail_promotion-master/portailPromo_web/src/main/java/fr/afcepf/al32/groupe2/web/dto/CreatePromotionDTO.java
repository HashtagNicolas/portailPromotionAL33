package fr.afcepf.al32.groupe2.web.dto;

import java.util.Date;

public class CreatePromotionDTO {

	private String promotionName;
	private String description;
	private Double quantityInitAvailable;
	private Long productId;
	private Boolean isCumulative; //par default false
	private Long promotionDuration;
	private Long productTakeAwayDuration;
	private Long idCommerce;
	private String typePromotion;
	private Long numberPurchase;
	private Long numberOffered;
	private Double discountValue;
	private Double minPurchaseAmountDiscount;
	private Double minPurchaseAmountPercent;
	private Double percentValue;
	
	public CreatePromotionDTO() {

	}

	public CreatePromotionDTO(String promotionName, String description, Double quantityInitAvailable, Long productId,
			Date dateCreation, Boolean isCumulative, Long promotionDuration, Long productTakeAwayDuration,
			Long idCommerce, String typePromotion, Long numberPurchase, Long numberOffered, Double discountValue,
			Double minPurchaseAmountDiscount, Double minPurchaseAmountPercent, Double percentValue) {
		super();
		this.promotionName = promotionName;
		this.description = description;
		this.quantityInitAvailable = quantityInitAvailable;
		this.productId = productId;
		this.isCumulative = isCumulative;
		this.promotionDuration = promotionDuration;
		this.productTakeAwayDuration = productTakeAwayDuration;
		this.idCommerce = idCommerce;
		this.typePromotion = typePromotion;
		this.numberPurchase = numberPurchase;
		this.numberOffered = numberOffered;
		this.discountValue = discountValue;
		this.minPurchaseAmountDiscount = minPurchaseAmountDiscount;
		this.minPurchaseAmountPercent = minPurchaseAmountPercent;
		this.percentValue = percentValue;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getQuantityInitAvailable() {
		return quantityInitAvailable;
	}

	public void setQuantityInitAvailable(Double quantityInitAvailable) {
		this.quantityInitAvailable = quantityInitAvailable;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getIsCumulative() {
		return isCumulative;
	}

	public void setIsCumulative(Boolean isCumulative) {
		this.isCumulative = isCumulative;
	}

	public Long getPromotionDuration() {
		return promotionDuration;
	}

	public void setPromotionDuration(Long promotionDuration) {
		this.promotionDuration = promotionDuration;
	}

	public Long getProductTakeAwayDuration() {
		return productTakeAwayDuration;
	}

	public void setProductTakeAwayDuration(Long productTakeAwayDuration) {
		this.productTakeAwayDuration = productTakeAwayDuration;
	}

	public Long getIdCommerce() {
		return idCommerce;
	}

	public void setIdCommerce(Long idCommerce) {
		this.idCommerce = idCommerce;
	}

	public String getTypePromotion() {
		return typePromotion;
	}

	public void setTypePromotion(String typePromotion) {
		this.typePromotion = typePromotion;
	}

	public Long getNumberPurchase() {
		return numberPurchase;
	}

	public void setNumberPurchase(Long numberPurchase) {
		this.numberPurchase = numberPurchase;
	}

	public Long getNumberOffered() {
		return numberOffered;
	}

	public void setNumberOffered(Long numberOffered) {
		this.numberOffered = numberOffered;
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Double getMinPurchaseAmountDiscount() {
		return minPurchaseAmountDiscount;
	}

	public void setMinPurchaseAmountDiscount(Double minPurchaseAmountDiscount) {
		this.minPurchaseAmountDiscount = minPurchaseAmountDiscount;
	}

	public Double getMinPurchaseAmountPercent() {
		return minPurchaseAmountPercent;
	}

	public void setMinPurchaseAmountPercent(Double minPurchaseAmountPercent) {
		this.minPurchaseAmountPercent = minPurchaseAmountPercent;
	}

	public Double getPercentValue() {
		return percentValue;
	}

	public void setPercentValue(Double percentValue) {
		this.percentValue = percentValue;
	}
	
	
}
