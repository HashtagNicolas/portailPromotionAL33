package fr.afcepf.al32.groupe2.web.dto;

import java.util.List;

import fr.afcepf.al32.groupe2.entity.CategoryProduct;

public class RequestSearchDTO {

	private List<CategoryProduct> categories;
	private String searchField; 		
	private String searchSourceAddress = "";
	private Integer searchPerimeter = 10;
			
			public RequestSearchDTO() {
		
	}
			
	public RequestSearchDTO(List<CategoryProduct> categories, String searchField, String searchSourceAddress,
			Integer searchPerimeter) {
		super();
		this.categories = categories;
		this.searchField = searchField;
		this.searchSourceAddress = searchSourceAddress;
		this.searchPerimeter = searchPerimeter;
	}
	public List<CategoryProduct> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryProduct> categories) {
		this.categories = categories;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchSourceAddress() {
		return searchSourceAddress;
	}
	public void setSearchSourceAddress(String searchSourceAddress) {
		this.searchSourceAddress = searchSourceAddress;
	}
	public Integer getSearchPerimeter() {
		return searchPerimeter;
	}
	public void setSearchPerimeter(Integer searchPerimeter) {
		this.searchPerimeter = searchPerimeter;
	}
	
	
}
