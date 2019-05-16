package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import javax.persistence.*;

@Embeddable
public class PercentType extends PromotionType {

	@Column(name = "percent_value")
	private Double percentValue;

	@Column(name = "min_purchase_amount")
	private Double minPurchaseAmount;

	public PercentType(Long promotionTypeId, Double percentValue, Double minPurchaseAmount) {
		super(promotionTypeId);
		this.percentValue = percentValue;
		this.minPurchaseAmount = minPurchaseAmount;
		this._class = this.getClass().getName();
	}

	public Double getPercentValue() {
		return percentValue;
	}

	public void setPercentValue(Double percentValue) {
		this.percentValue = percentValue;
	}

	public Double getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	public void setMinPurchaseAmount(Double minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

	@Override
	public String getName() {
		return "Pourcentage";
	}
}
