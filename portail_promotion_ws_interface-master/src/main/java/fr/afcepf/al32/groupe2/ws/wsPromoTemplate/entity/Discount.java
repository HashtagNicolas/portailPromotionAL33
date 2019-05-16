package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class Discount extends PromotionType {

	@Column(name = "discount_value")
	@NotNull
	@Min(0)
	private Double discountValue;

	@Column(name = "min_purchase_amount")
	private Double minPurchaseAmount;

	public Discount(Long promotionTypeId, @NotNull @Min(0) Double discountValue, Double minPurchaseAmount) {
		super(promotionTypeId);
		this.discountValue = discountValue;
		this.minPurchaseAmount = minPurchaseAmount;
		this._class = this.getClass().getName();
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Double getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	public void setMinPurchaseAmount(Double minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

	@Override
	public String getName() {
		return "Remise";
	}
}
