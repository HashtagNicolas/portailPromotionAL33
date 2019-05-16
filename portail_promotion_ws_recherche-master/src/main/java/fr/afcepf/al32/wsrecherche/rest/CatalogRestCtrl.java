package fr.afcepf.al32.wsrecherche.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al32.groupe2.ws.dto.CategoryAndKeywordsDto;
import fr.afcepf.al32.groupe2.ws.dto.CategoryProductDto;
import fr.afcepf.al32.groupe2.ws.dto.SearchByCategoryAndKeywordsResponseDto;
import fr.afcepf.al32.groupe2.ws.dto.SearchByCategoryResponseDto;
import fr.afcepf.al32.groupe2.ws.dto.SearchByKeywordsResponseDto;
import fr.afcepf.al32.groupe2.ws.dto.SearchByShopResponseDto;
import fr.afcepf.al32.groupe2.ws.dto.ShopDto;
import fr.afcepf.al32.wsrecherche.service.itf.ICatalogService;

@RestController
@RequestMapping(value="/rest/promotionProduct", headers="Accept=application/json")
public class CatalogRestCtrl {


	@Autowired
	private ICatalogService catalogService;

	@PostMapping("/byCategoryAndKeywords")
	public SearchByCategoryAndKeywordsResponseDto getSearchByCategoryAndKeywords(@RequestBody CategoryAndKeywordsDto categoryAndKeywordsDto) {
		return catalogService.searchByCategoryAndKeyWords(categoryAndKeywordsDto.getCategoryProductDto(),categoryAndKeywordsDto.getKeyWords());
	}
		
	@PostMapping("/byKeywords")
	public SearchByKeywordsResponseDto getSearchByKeywords(@RequestBody List<String> keyWords) {
		return catalogService.searchByKeyWords(keyWords);
	}

	@PostMapping("/byCategory")
	public SearchByCategoryResponseDto getSearchByCategory(@RequestBody CategoryProductDto categoryProductDto) {
		return catalogService.searchByCategory(categoryProductDto) ;
	}

	@PostMapping("/byShop")
	public SearchByShopResponseDto getSearchByShop(@RequestBody List<ShopDto> shop) {
		return catalogService.searchByShop(shop) ;
	}

}
