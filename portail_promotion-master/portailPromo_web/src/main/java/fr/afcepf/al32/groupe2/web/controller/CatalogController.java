package fr.afcepf.al32.groupe2.web.controller;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.List;
	import java.util.stream.Collectors;

	import javax.annotation.PostConstruct;
	import javax.transaction.Transactional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.afcepf.al32.groupe2.entity.CategoryProduct;
	import fr.afcepf.al32.groupe2.entity.Client;
	import fr.afcepf.al32.groupe2.entity.Promotion;
	import fr.afcepf.al32.groupe2.entity.User;
	import fr.afcepf.al32.groupe2.service.ICatalogService;
	import fr.afcepf.al32.groupe2.service.IServiceCategoryProduct;
	import fr.afcepf.al32.groupe2.service.IServicePromotion;
	import fr.afcepf.al32.groupe2.web.connexion.ConnectionBean;
import fr.afcepf.al32.groupe2.web.dto.RequestSearchDTO;
import fr.afcepf.al32.groupe2.web.dto.ResultSearchDTO;
import fr.afcepf.al32.groupe2.ws.dto.CategoryProductDto;
	import fr.afcepf.al32.groupe2.ws.dto.OrchestratorResearchDtoResponse;
	import fr.afcepf.al32.groupe2.ws.dto.PromotionDto;
	import fr.afcepf.al32.groupe2.ws.itf.IWsPromoTemplate;
	import fr.afcepf.al32.groupe2.ws.itf.IWsRecherche;
	import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;
	import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.TopPromotionTemplateResultDto;


	@CrossOrigin(origins = "*", maxAge = 3600)
	@RestController
	@RequestMapping("/api/catalog")
	public class CatalogController {

		
		@Autowired
		private ICatalogService catalogService;

		@Autowired
		private IServicePromotion servicePromotion;

		@Autowired
		private IServiceCategoryProduct serviceCategoryProduct;

		@Autowired
		private IWsRecherche rechercheDelegate;

		private List<Promotion> promotions = new ArrayList<>();

		private ResultSearchDTO resultSearchDTO = new ResultSearchDTO();

		private String selectedCategory;



		/**
		 * Message d'avertissement si l'adresse n'existe pas.
		 */
		private String addressWarning;


		private  double latitudeVilleSelec;
		private double longitudeVilleSelec;

	
		
		
		@PostMapping("/research")
		public ResultSearchDTO Search (@RequestBody RequestSearchDTO requestSearchDTO ) {
			List<String> keyWords = Arrays.asList(requestSearchDTO.getSearchField().split(" "));
			CategoryProduct category = requestSearchDTO.getCategories().stream()
					.filter(categoryProduct -> categoryProduct.getName().equals(selectedCategory)).findFirst().orElse(null);
			OrchestratorResearchDtoResponse orchestratorResponse = rechercheDelegate.searchListPromotion(
					requestSearchDTO.getSearchSourceAddress(), requestSearchDTO.getSearchPerimeter(), keyWords,
					category == null ? null : new CategoryProductDto(category.getId()));

			if (!orchestratorResponse.getPromotions().isEmpty()) {
				resultSearchDTO.setPromotionList(servicePromotion.findAllValidByIds(orchestratorResponse.getPromotions().stream()
						.map(PromotionDto::getId).collect(Collectors.toList())));
			} else {
				resultSearchDTO.setPromotionList(Collections.emptyList());
			}

			if (!orchestratorResponse.getAddressValid()) {
				resultSearchDTO.setAddressWarning("Adresse non trouvée"); 
			} else {
				resultSearchDTO.setAddressWarning(""); 
			}


			if(orchestratorResponse.getCoordonnees().size() == 0)
			{
					resultSearchDTO.setLatitude(0);
					resultSearchDTO.setLongitude(0);

			}
			else {
				resultSearchDTO.setLatitude(orchestratorResponse.getCoordonnees().get(0));
				resultSearchDTO.setLongitude(orchestratorResponse.getCoordonnees().get(1));
			}


			return resultSearchDTO;
		}


		@GetMapping("/allPromotions")
		public List<Promotion> initCatalogProduits() {
			List<Promotion>Test = new ArrayList<Promotion>();
			Test = getAllPromotions();
			return Test;

		

		}
		@GetMapping("/allCategories")
		public List<CategoryProduct> initComboboxCategorie() {
			return getAllRootCategories().stream().sorted(Comparator.comparing(CategoryProduct::getName))
					.collect(Collectors.toList());
		}
		

		public ICatalogService getCatalogService() {
			return catalogService;
		}

		public void setCatalogService(ICatalogService catalogService) {
			this.catalogService = catalogService;
		}

		public List<Promotion> getPromotions() {
			return promotions;
		}

		public void setPromotions(List<Promotion> promotions) {
			this.promotions = promotions;
		}

		public List<Promotion> getAllPromotions() {
			return catalogService.getAllDisplayablePromotion();
		}

		public List<CategoryProduct> getAllRootCategories() {
			return catalogService.getAllRootCategories();
		}


		public String getSelectedCategory() {
			return selectedCategory;
		}

		public void setSelectedCategory(String selectedCategory) {
			this.selectedCategory = selectedCategory;
		}


		public String getAddressWarning() {
			return addressWarning;
		}

		public void setAddressWarning(String addressWarning) {
			this.addressWarning = addressWarning;
		}

		public IServicePromotion getServicePromotion() {
			return servicePromotion;
		}

		public void setServicePromotion(IServicePromotion servicePromotion) {
			this.servicePromotion = servicePromotion;
		}

		public IServiceCategoryProduct getServiceCategoryProduct() {
			return serviceCategoryProduct;
		}

		public void setServiceCategoryProduct(IServiceCategoryProduct serviceCategoryProduct) {
			this.serviceCategoryProduct = serviceCategoryProduct;
		}

		public IWsRecherche getRechercheDelegate() {
			return rechercheDelegate;
		}

		public void setRechercheDelegate(IWsRecherche rechercheDelegate) {
			this.rechercheDelegate = rechercheDelegate;
		}


		public double getLatitudeVilleSelec() {
			return latitudeVilleSelec;
		}

		public void setLatitudeVilleSelec(double latitudeVilleSelec) {
			this.latitudeVilleSelec = latitudeVilleSelec;
		}

		public double getLongitudeVilleSelec() {
			return longitudeVilleSelec;
		}

		public void setLongitudeVilleSelec(double longitudeVilleSelec) {
			this.longitudeVilleSelec = longitudeVilleSelec;
		}


	}
	
	
