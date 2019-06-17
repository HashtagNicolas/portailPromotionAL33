package fr.afcepf.al32.groupe2.web.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al32.groupe2.entity.BaseProduct;
import fr.afcepf.al32.groupe2.entity.Discount;
import fr.afcepf.al32.groupe2.entity.Pack;
import fr.afcepf.al32.groupe2.entity.PercentType;
import fr.afcepf.al32.groupe2.entity.Product;
import fr.afcepf.al32.groupe2.entity.Promotion;
import fr.afcepf.al32.groupe2.entity.Publish;
import fr.afcepf.al32.groupe2.entity.ReferenceProduct;
import fr.afcepf.al32.groupe2.entity.Shop;
import fr.afcepf.al32.groupe2.entity.Shopkeeper;
import fr.afcepf.al32.groupe2.service.IAuthenticationService;
import fr.afcepf.al32.groupe2.service.IFollowableElementService;
import fr.afcepf.al32.groupe2.service.IServiceBaseProduct;
import fr.afcepf.al32.groupe2.service.IServicePublish;
import fr.afcepf.al32.groupe2.service.impl.EmailWServiceImpl;
import fr.afcepf.al32.groupe2.web.dto.CreatePromotionDTO;
import fr.afcepf.al32.groupe2.ws.itf.IWsPromoTemplate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@SessionScope
@RequestMapping("/api/addpromotion")
public class AddPromotionController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	private IServicePublish servicePublish;
	
	
	@Autowired
	private IServiceBaseProduct serviceBaseProduct;
	
	@Autowired
	private IFollowableElementService followableElementService;

// bnm - 15/05/2019 implémentation envoi de courriels par web service
	@Autowired
	 EmailWServiceImpl emailService;

	//private List<PromotionTemplateResultDto> topPromoTemplates;

	/**
	 * Id of the product link to the created promotion
	 */
	private Long productId;

	/**
	 * Type of the promotion.
	 */
	private String typePromotion = "Pourcentage";

	/**
	 * For percent type promotion, value of the percentage of reduction.
	 */
	private Double percentValue;

	/**
	 * For discount type promotion, value of the discount in €.
	 */
	private Double discountValue;

	/**
	 * For discount and percent type promotion, minimum of purchase needed to enjoy the promotion, in €.
	 */
	private Double minPurchaseAmount;

	/**
	 * Initial quantity of product available for the promotion.
	 */
	private Double initQuantityAvailable;

	/**
	 * Name of the promotion.
	 */
	private String promotionName;

	/**
	 * Description of the promotion.
	 */
	private String promotionDescription;

	private Long numberPurchase;

	private Long numberOffered;

	/**
	 * Durée de la promotion en jours.
	 */
	private Long promotionDuration;

	/**
	 * Durée de retrait en heures.
	 */
	private Long productTakeAwayDuration;
	
	private Long commerceId;
	
	private List<Shop> shops;
	
	private List<BaseProduct> products;
	
	private List<Promotion> listPromo;
	
	@PostMapping("/create/{idCommercant}")
	public CreatePromotionDTO create(@PathVariable Long idCommercant, @RequestBody  CreatePromotionDTO createPromotionDTO) {
		Promotion promotion = new Promotion();
		promotion.setName(createPromotionDTO.getPromotionName());
		promotion.setDescription(createPromotionDTO.getDescription());
		promotion.setQuantityInitAvailable(createPromotionDTO.getQuantityInitAvailable());
		BaseProduct baseProduct = serviceBaseProduct.rechercheBaseProductParIdentifiant(createPromotionDTO.getProductId());
		Product product = baseProduct.getLastProduct();
		promotion.setProduct(product);
		promotion.setDateCreation(new Date());
		promotion.setIsCumulative(false);
		promotion.setLimitTimePromotion(Duration.ofDays(createPromotionDTO.getPromotionDuration()));
		promotion.setLimitTimeTakePromotion(Duration.ofHours(createPromotionDTO.getProductTakeAwayDuration()));
		promotion.setQuantityRemaining(createPromotionDTO.getQuantityInitAvailable());
		
		Map<Long, Shop> shopMap = new HashMap<>();
		Shopkeeper shopkeeper = (Shopkeeper) authenticationService.findOneById(idCommercant);
		Shop shop = shopkeeper.getShops().get(createPromotionDTO.getIdCommerce());
		shopMap.put(shop.getId(), shop);
		promotion.setShops(shopMap);

		switch (createPromotionDTO.getTypePromotion()){
			case "Pourcentage":
				createPercentagePromotion(promotion, createPromotionDTO.getMinPurchaseAmountPercent(), createPromotionDTO.getPercentValue() );
				break;
			case "Remise":
				createDiscountPromotion(promotion, createPromotionDTO.getDiscountValue(), createPromotionDTO.getMinPurchaseAmountDiscount() );
				break;
			case "Pack":
				createPackPromotion(promotion, createPromotionDTO.getNumberPurchase().intValue(), createPromotionDTO.getNumberOffered().intValue());
				break;
		}

		Publish publish = new Publish();
		publish.setPublishDate(new Date());
		publish.setPromotion(promotion);
		
		servicePublish.create(publish);
		
		followableElementService.notifySubscribers(shop);
		 // bnm - 15/05/2019 implémentation envoi de courriels par web service
		emailService.sendEmailPromoOwner( shopkeeper, promotion);
		
		return createPromotionDTO;
	}
	
	@GetMapping("/productList")
	public List<BaseProduct> referenceProductList () {
	return serviceBaseProduct.findAll();
	}
	
	
	private void createPackPromotion(Promotion promotion, int numberPurchase , int numberOffered) {
		Pack packType = new Pack();
		packType.setNumberPurchased(numberPurchase);
		packType.setNumberOffered(numberOffered);
		promotion.setPromotionType(packType);
	}

	private void createDiscountPromotion(Promotion promotion,  double discountValue, double minPurchaseAmountDiscount ) {
		Discount discountType = new Discount();
		discountType.setDiscountValue(discountValue);
		discountType.setMinPurchaseAmount(minPurchaseAmountDiscount);
		promotion.setPromotionType(discountType);
	}

	private void createPercentagePromotion(Promotion promotion, double minPurchaseAmount, double percentValue ) {
		PercentType percentType = new PercentType();
		percentType.setMinPurchaseAmount(minPurchaseAmount);
		percentType.setPercentValue(percentValue);
		promotion.setPromotionType(percentType);
	}


	public String getTypePromotion() {
		return typePromotion;
	}

	public void setTypePromotion(String typePromotion) {
		this.typePromotion = typePromotion;
	}

	public Double getPercentValue() {
		return percentValue;
	}

	public void setPercentValue(Double percentValue) {
		this.percentValue = percentValue;
	}

	public Double getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	public void setMinPurchaseAmount(Double minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

	public Long getCommerceId() {
		return commerceId;
	}

	public void setCommerceId(Long commerceId) {
		this.commerceId = commerceId;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public List<BaseProduct> getProducts() {
		return products;
	}

	public void setProducts(List<BaseProduct> products) {
		this.products = products;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Long getNumberPurchase() {
		return numberPurchase;
	}

	public void setNumberPurchase(Long numberPurchase) {
		this.numberPurchase = numberPurchase;
	}

	public Long getNumberOffered() {
		return numberOffered;
	}

	public void setNumberOffered(Long numberOffered) {
		this.numberOffered = numberOffered;
	}

	public Long getPromotionDuration() {
		return promotionDuration;
	}

	public void setPromotionDuration(Long promotionDuration) {
		this.promotionDuration = promotionDuration;
	}

	public Long getProductTakeAwayDuration() {
		return productTakeAwayDuration;
	}

	public void setProductTakeAwayDuration(Long productTakeAwayDuration) {
		this.productTakeAwayDuration = productTakeAwayDuration;
	}

	public Double getInitQuantityAvailable() {
		return initQuantityAvailable;
	}

	public void setInitQuantityAvailable(Double initQuantityAvailable) {
		this.initQuantityAvailable = initQuantityAvailable;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

//	public List<PromotionTemplateResultDto> getTopPromoTemplates() {
//		return topPromoTemplates;
//	}
//
//	public void setTopPromoTemplates(List<PromotionTemplateResultDto> topPromoTemplates) {
//		this.topPromoTemplates = topPromoTemplates;
//	}
	
	
	public List<Promotion> getListPromo() {
		return listPromo;
	}

	public void setListPromo(List<Promotion> listPromo) {
		this.listPromo = listPromo;
	}
	
}
