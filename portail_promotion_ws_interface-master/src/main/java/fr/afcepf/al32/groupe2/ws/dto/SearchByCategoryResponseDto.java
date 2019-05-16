package fr.afcepf.al32.groupe2.ws.dto;

import java.util.List;


public class SearchByCategoryResponseDto {

	private List<PromotionDto> promotionsDto;

	private CategoryProductDto categoryProductDto;

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	public CategoryProductDto getCategoryProductDto() {
		return categoryProductDto;
	}

	public void setCategoryProductDto(CategoryProductDto categoryProductDto) {
		this.categoryProductDto = categoryProductDto;
	}

	public SearchByCategoryResponseDto(List<PromotionDto> promotionsDto, CategoryProductDto categoryProductDto) {
		super();
		this.promotionsDto = promotionsDto;
		this.categoryProductDto = categoryProductDto;
	}

	public SearchByCategoryResponseDto() {
		super();
	}
	
	

}
