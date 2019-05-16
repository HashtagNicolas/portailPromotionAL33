package fr.afcepf.al32.groupe2.ws.dto;

import java.util.List;


public class SearchByCategoryAndKeywordsResponseDto {
	private List<PromotionDto> promotionsDto;

	private CategoryProductDto categoryProductDto;

	private List<String> keyWords;

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	public CategoryProductDto getCategoryProductDto() {
		return categoryProductDto;
	}

	public SearchByCategoryAndKeywordsResponseDto() {
		super();
	}

	public SearchByCategoryAndKeywordsResponseDto(List<PromotionDto> promotionsDto,
			CategoryProductDto categoryProductDto, List<String> keyWords) {
		super();
		this.promotionsDto = promotionsDto;
		this.categoryProductDto = categoryProductDto;
		this.keyWords = keyWords;
	}

	public void setCategoryProductDto(CategoryProductDto categoryProductDto) {
		this.categoryProductDto = categoryProductDto;
	}

	public List<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}


}
