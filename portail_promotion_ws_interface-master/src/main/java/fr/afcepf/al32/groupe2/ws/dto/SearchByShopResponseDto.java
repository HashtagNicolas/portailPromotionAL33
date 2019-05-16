package fr.afcepf.al32.groupe2.ws.dto;

import java.util.List;


public class SearchByShopResponseDto {

	private List<PromotionDto> promotionsDto;
	
	private List<ShopDto> shopDtos;

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	public List<ShopDto> getShopDtos() {
		return shopDtos;
	}

	public void setShopDtos(List<ShopDto> shopDtos) {
		this.shopDtos = shopDtos;
	}

	public SearchByShopResponseDto(List<PromotionDto> promotionsDto, List<ShopDto> shopDtos) {
		super();
		this.promotionsDto = promotionsDto;
		this.shopDtos = shopDtos;
	}

	public SearchByShopResponseDto() {
		super();
	}
	
	

}
