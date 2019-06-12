package fr.afcepf.al32.groupe2.entity;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.afcepf.al32.groupe2.util.SubscriberType;

@Entity
@DiscriminatorValue(value="shopkeeper")
public class Shopkeeper extends User implements ISubscriber{
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@MapKey(name="id")
	@JsonIgnore
	private Map<Long, Shop> shops;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
	@MapKey(name="id")
	@JsonIgnore
	private Map<Long, TemplatePromotion> templatesPromotion;

	public Map<Long, Shop> getShops() {
		return shops;
	}

	public void setShops(Map<Long, Shop> shops) {
		this.shops = shops;
	}

	public Map<Long, TemplatePromotion> getTemplatesPromotion() {
		return templatesPromotion;
	}

	public void setTemplatesPromotion(Map<Long, TemplatePromotion> templatesPromotion) {
		this.templatesPromotion = templatesPromotion;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return SubscriberType.SHOPKEEPER;
	}

	
	
}
