package fr.afcepf.al32.groupe2.ws.dto;

import java.util.List;


public class SearchByKeywordsResponseDto {

	private List<PromotionDto> promotionsDto;

	private List<String> keyWords;

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	public List<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}

	public SearchByKeywordsResponseDto() {
		super();
	}

	
}
