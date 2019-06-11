package fr.afcepf.al32.groupe2.web.dto;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.al32.groupe2.entity.Promotion;

public class ResultSearchDTO {

	
	private List<Promotion> promotionList = new ArrayList<Promotion>();
	private String addressWarning;
	private double longitude;
	private double latitude;
	
	public ResultSearchDTO() {
		
	}
	
	public ResultSearchDTO(List<Promotion> promotionList, String addressWarning, double longitude, double latitude) {
		super();
		this.promotionList = promotionList;
		this.addressWarning = addressWarning;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public List<Promotion> getPromotionList() {
		return promotionList;
	}
	public void setPromotionList(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}
	public String getAddressWarning() {
		return addressWarning;
	}
	public void setAddressWarning(String addressWarning) {
		this.addressWarning = addressWarning;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
}
