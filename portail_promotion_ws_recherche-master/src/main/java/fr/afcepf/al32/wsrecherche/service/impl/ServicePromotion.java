package fr.afcepf.al32.wsrecherche.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al32.groupe2.ws.dto.ShopDto;
import fr.afcepf.al32.wsrecherche.dao.itf.IPromotionDao;
import fr.afcepf.al32.wsrecherche.entity.BaseProduct;
import fr.afcepf.al32.wsrecherche.entity.Promotion;
import fr.afcepf.al32.wsrecherche.service.itf.IServicePromotion;

@Transactional
@Component
public class ServicePromotion implements IServicePromotion {
	@Autowired
	private IPromotionDao promotiondao;

	@Override
	public List<Promotion> findAll() {
		return promotiondao.findAll();
	}

	@Override
	public List<Promotion> findAllValid() {
		return promotiondao.findAllValid();
	}

	@Override
	public Promotion recherchePromotionParIdentifiant(Long idUnite) {
		return promotiondao.findOne(idUnite);
	}

	@Override
	public Promotion ajouterPromotion(Promotion promotion) {
		promotiondao.createOne(promotion);
		return promotion;
	}

	@Override
	public List<Promotion> getAllValidPromotionByProduct(List<BaseProduct> products) {
		List<Promotion> validPromos = promotiondao.findAllValid();
		List<Promotion> result = new ArrayList<>();

		for (Promotion promotion : validPromos) {
			for (BaseProduct product : products) {
				if(product.getId().equals(promotion.getBaseProduct().getId())) {
					result.add(promotion);
					break;
				}
			}
		}
		return result;
	}

	@Override
	public List<Promotion> getAllValidPromotionByShop(List<ShopDto> shops) {
		List<Promotion> validPromos = promotiondao.findAllValid();
		List<Promotion> result = new ArrayList<>();
		for (Promotion promotion : validPromos) {
			for (ShopDto shop : shops) {
				if(promotion.getShops().get(shop.getId()) !=null) {
					result.add(promotion);
					break;
				}
			}
		}

		return result;
	}

}
