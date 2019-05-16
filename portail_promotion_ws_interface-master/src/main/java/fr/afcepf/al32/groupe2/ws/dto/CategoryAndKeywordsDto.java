package fr.afcepf.al32.groupe2.ws.dto;

import java.util.List;


public class CategoryAndKeywordsDto {
	private CategoryProductDto categoryProductDto;

	private List<String> keyWords;
	
	public CategoryAndKeywordsDto() {
		super();
	}

	public CategoryAndKeywordsDto(CategoryProductDto categoryProductDto, List<String> keyWords){
		this.categoryProductDto = categoryProductDto;
		this.keyWords = keyWords;
	}

	public CategoryProductDto getCategoryProductDto() {
		return categoryProductDto;
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
