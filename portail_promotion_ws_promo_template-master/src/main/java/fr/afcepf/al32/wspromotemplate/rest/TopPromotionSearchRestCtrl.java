package fr.afcepf.al32.wspromotemplate.rest;

import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.PromotionTemplateResultDto;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.dto.TopPromotionTemplateResultDto;
import fr.afcepf.al32.groupe2.ws.wsPromoTemplate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import fr.afcepf.al32.wspromotemplate.service.itf.IPromotionSearchService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/rest/Template", headers="Accept=application/json")
public class TopPromotionSearchRestCtrl {

	@Autowired
	private IPromotionSearchService promotionSearchService;

	@Autowired
	private MongoOperations mongoOps;

	@GetMapping("/ByShopper")
	public TopPromotionTemplateResultDto getSearchByShopper(@RequestParam String sourceLong, @RequestParam String sourceLat, @RequestParam List<String> categories) {

		List<String> decodedCategories = categories.stream().map(category -> {
			try {
				String decodedCategory = URLDecoder.decode(category, "UTF-8");
				return String.format("'%s'", decodedCategory);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "";
		}).collect(Collectors.toList());
		List<PromotionTemplateResultDto> result = promotionSearchService.searchTopPromotionByShopKeeper(Double.parseDouble(sourceLong), Double.parseDouble(sourceLat), decodedCategories);

		return new TopPromotionTemplateResultDto(Double.parseDouble(sourceLong), Double.parseDouble(sourceLat), decodedCategories, result);
	}

	/**
	 * Méthode servant à insérer deux entrées différentes dans la base de données MongoDB
	 * pour avoir un retour avec la méthode de recherche.
	 */
	@PostMapping("/create")
	public void createData() {
		Promotion promo = new Promotion(1L, "promo1", "promo1 desc", new Date(), Duration.ofDays(2L), Duration.ofHours(2), 50.0, false,
				new PercentType(1L, 20.0, 1.0), new Product(1L, 1L, 20.0, "produit1 desc", new Date(), "produit1", new ProductCategory("Prêt-à-porter", Arrays.asList("Homme", "Femme"), null)));

		promo.getShops().add(new Shop(1L, new Point(2.3571216637046, 48.8586639350234), 1L));
		promo.getReservations().add(new Reservation(1L, new Date(), 2.0, new Client(1L, new Point(-20.0, 125.0))));

		Promotion promo2 = new Promotion(2L, "promo2", "promo2 desc", new Date(), Duration.ofDays(5L), Duration.ofHours(3), 25.0, false,
				new Pack(2L, 3, 1), new Product(1L, 1L, 20.0, "produit1 desc", new Date(), "produit1", new ProductCategory("Prêt-à-porter", Arrays.asList("Homme", "Femme"), null)));

		promo2.getShops().add(new Shop(1L, new Point(2.3571216637046, 48.8586639350234), 1L));
		promo2.getReservations().add(new Reservation(1L, new Date(), 2.0, new Client(1L, new Point(-20.0, 125.0))));

		mongoOps.insert(promo, "promotions");
		mongoOps.insert(promo2, "promotions");

		Query query = new Query(Criteria.where("product.category.libelle").is("Prêt-à-porter").andOperator(Criteria.where("product.category.ancestors").is("Homme")));
		List<Promotion> promotions = mongoOps.find(query, Promotion.class, "promotions");
		promotions.forEach(x -> System.out.println(x.getId()));

	}

	@PostMapping("/massCreate")
	public void createLargeData() {

		List<Shop> shopList = createShopList();

		List<Product> productList = createProductList();

		List<PromotionType> promotionTypeList = createpromotionTypeList();

		for (int i =0; i< 100000; i++){

			Long randomDay = Math.round(Math.random() * 10 + 1);
			Long randomHours = Math.round(Math.random() * 10 + 1);

			Double randomReservedProductPercentage = Math.random() *100;

			Random rand = new Random();

			Promotion promo = new Promotion(Integer.toUnsignedLong(i +1), String.format("promotion-%s", Integer.toUnsignedLong(i +1)), String.format("description promotion-%s", Integer.toUnsignedLong(i +1)), new Date(), Duration.ofDays(randomDay), Duration.ofHours(randomHours), randomReservedProductPercentage, false,
					promotionTypeList.get(rand.nextInt(promotionTypeList.size())), productList.get(rand.nextInt(productList.size())));

			promo.getShops().add(shopList.get(rand.nextInt(shopList.size())));

			int reservationIterator = rand.nextInt(20);
			for (int j =0; j < reservationIterator; j++){
				promo.getReservations().add(new Reservation(Integer.toUnsignedLong(j), new Date(), 1.0, new Client(1L, new Point(-20.0, 125.0))));
			}

			mongoOps.insert(promo, "promotions");
			System.out.println(i);
		}

	}

	private List<Shop> createShopList(){
		List<Shop> list = new ArrayList<>();

		Shop shop1 = new Shop(1L, new Point(2.3571216637046, 48.8586639350234), 4L);
		Shop shop2 = new Shop(2L, new Point(2.33691562915772, 48.8396896294951), 4L);
		Shop shop3 = new Shop(3L, new Point(2.32548231081664, 48.8453195864037), 5L);
		Shop shop4 = new Shop(4L, new Point(2.29440521876441, 48.8574462899748), 5L);
		Shop shop5 = new Shop(5L, new Point(2.32103774074187, 48.8778194416936), 5L);
		Shop shop6 = new Shop(6L, new Point(2.34450928619433, 48.8712843739922), 6L);
		Shop shop7 = new Shop(7L, new Point(2.36703256602569, 48.8697175394012), 7L);
		Shop shop8 = new Shop(8L, new Point(2.38783302611709, 48.8593052330986), 8L);
		Shop shop9 = new Shop(9L, new Point(2.36830202338274, 48.8474384006745), 9L);

		list.add(shop1);
		list.add(shop2);
		list.add(shop3);
		list.add(shop4);
		list.add(shop5);
		list.add(shop6);
		list.add(shop7);
		list.add(shop8);
		list.add(shop9);

		return list;
	}

	private List<Product> createProductList(){
		List<Product> list = new ArrayList<>();

		Product product1 = new Product(1L, 1L, 20.0, "Le classique du jean, toujours à la mode.", new Date(), "Jean Levi's 501", new ProductCategory("Jeans / Pantalons / Shorts / Leggings", Arrays.asList("Femme", "Prêt-à-porter"), "Femme"));
		Product product2 = new Product(2L, 2L, 50.0, "Importé directement d'Ecosse", new Date(), "Chaussettes noires en fil d'Ecosse", new ProductCategory("Chaussettes / collants", Arrays.asList("Femme", "Prêt-à-porter"), "Femme"));
		Product product3 = new Product(3L, 3L, 20.0, "Pour dormir à l'aise", new Date(), "Pyjama rayés homme taille 42", new ProductCategory("Pyjamas /Nuisettes", Arrays.asList("Femme", "Prêt-à-porter"), "Femme"));
		Product product4 = new Product(4L, 4L, 30.0, "Aéré au niveau des aisselles", new Date(), "T-shirt sports Nike taille L", new ProductCategory("Sports", Arrays.asList("Femme", "Prêt-à-porter"), "Femme"));
		Product product5 = new Product(5L, 5L, 35.0, "Idéal pour se baigner en Bretagne", new Date(), "Maillot de bain isotherme", new ProductCategory("Maillots de bain / Peignoirs", Arrays.asList("Femme", "Prêt-à-porter"), "Femme"));
		Product product6 = new Product(6L, 6L, 72.0, "L'indispensable du golfeur", new Date(), "Polo Lacoste Bleu pervenche", new ProductCategory("T-shirts / Polos", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));
		Product product7 = new Product(7L, 7L, 85.0, "Pour rendre hommage à Elvis", new Date(), "Chemise hawaïenne rétro taille 50", new ProductCategory("Chemises", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));
		Product product8 = new Product(8L, 8L, 99.0, "Il est tout doux", new Date(), "Pull en cachemire", new ProductCategory("Pulls / gilets", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));
		Product product9 = new Product(9L, 9L, 15.0, "Idéal pour faire de la moto", new Date(), "Veste en cuir taille M", new ProductCategory("Vestes", Arrays.asList("Homme", "Femme"), null));
		Product product10 = new Product(10L, 10L, 13.0, "Le cadeau idéal pour votre amoureuse", new Date(), "Manteau en vison", new ProductCategory("Manteaux", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));
		Product product11 = new Product(11L, 11L, 144.0, null, new Date(), "Pantalon taille haute marron", new ProductCategory("Jeans / Pantalons / Shorts", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));
		Product product12 = new Product(12L, 12L, 59.0, null, new Date(), "Cravate rayée rouge et blanc", new ProductCategory("Costumes / Cravates", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));
		Product product13 = new Product(13L, 13L, 73.0, null, new Date(), "Caleçon en soie rouge", new ProductCategory("Sous-vêtements/pyjamas", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));
		Product product14 = new Product(14L, 14L, 88.0, null, new Date(), "Bandeau de sport absorbant", new ProductCategory("Sports", Arrays.asList("Homme", "Prêt-à-porter"), "Homme"));

		list.add(product1);
		list.add(product2);
		list.add(product3);
		list.add(product4);
		list.add(product5);
		list.add(product6);
		list.add(product7);
		list.add(product8);
		list.add(product9);
		list.add(product10);
		list.add(product11);
		list.add(product12);
		list.add(product13);
		list.add(product14);

		return list;
	}

	private List<PromotionType> createpromotionTypeList(){
		List<PromotionType> list = new ArrayList<>();

		PromotionType promotionType1 = new Pack(1L, 2, 1);
		PromotionType promotionType2 = new Pack(2L, 3, 1);
		PromotionType promotionType3 = new PercentType(3L, 10.0, 1.0);
		PromotionType promotionType4 = new PercentType(4L, 30.0, 2.0);
		PromotionType promotionType5 = new PercentType(5L, 50.0, 1.0);
		PromotionType promotionType6 = new PercentType(6L, 40.0, 1.0);
		PromotionType promotionType7 = new PercentType(7L, 25.0, 1.0);
		PromotionType promotionType8 = new PercentType(8L, 20.0, 1.0);
		PromotionType promotionType9 = new PercentType(9L, 5.0, 1.0);
		PromotionType promotionType10 = new PercentType(10L, 60.0, 1.0);

		list.add(promotionType1);
		list.add(promotionType2);
		list.add(promotionType3);
		list.add(promotionType4);
		list.add(promotionType5);
		list.add(promotionType6);
		list.add(promotionType7);
		list.add(promotionType8);
		list.add(promotionType9);
		list.add(promotionType10);

		return list;
	}
}
