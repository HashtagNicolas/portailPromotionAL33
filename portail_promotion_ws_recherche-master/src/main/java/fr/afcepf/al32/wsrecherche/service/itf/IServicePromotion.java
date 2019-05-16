package fr.afcepf.al32.wsrecherche.service.itf;

import java.util.List;

import fr.afcepf.al32.groupe2.ws.dto.ShopDto;
import fr.afcepf.al32.wsrecherche.entity.BaseProduct;
import fr.afcepf.al32.wsrecherche.entity.Promotion;

public interface IServicePromotion {
	List<Promotion> findAll();
	List<Promotion> findAllValid();
	Promotion recherchePromotionParIdentifiant(Long idUnite);
	Promotion ajouterPromotion(Promotion promotion);
	List<Promotion> getAllValidPromotionByProduct(List<BaseProduct> products);
	List<Promotion> getAllValidPromotionByShop(List<ShopDto> shops);
}
	