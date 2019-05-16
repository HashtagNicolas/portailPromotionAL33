package fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity;

import javax.persistence.*;

@Embeddable
public class Pack extends PromotionType {
	
	@Column(name="number_purchased")
	private Integer numberPurchased;
	
	@Column(name="number_offered")
	private Integer numberOffered;

	public Pack(Long promotionTypeId, Integer numberPurchased, Integer numberOffered) {
		super(promotionTypeId);
		this.numberPurchased = numberPurchased;
		this.numberOffered = numberOffered;
		this._class = this.getClass().getName();
	}

	public Integer getNumberPurchased() {
		return numberPurchased;
	}
	public void setNumberPurchased(Integer numberPurchased) {
		this.numberPurchased = numberPurchased;
	}
	public Integer getNumberOffered() {
		return numberOffered;
	}
	public void setNumberOffered(Integer numberOffered) {
		this.numberOffered = numberOffered;
	}

	@Override
	public String getName() {
		return "Pack";
	}
}
