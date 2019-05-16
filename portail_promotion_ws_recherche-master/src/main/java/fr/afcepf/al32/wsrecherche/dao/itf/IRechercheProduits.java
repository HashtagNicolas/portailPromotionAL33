package fr.afcepf.al32.wsrecherche.dao.itf;

import java.util.Date;
import java.util.List;

import fr.afcepf.al32.wsrecherche.entity.BaseProduct;

public interface IRechercheProduits extends IGenericDao<BaseProduct> {
		public List<BaseProduct> rechercherProduitAvecReferenceId(Long idProduct);
		public List<BaseProduct> rechercherProduitAvecCriteres(
															Date rqDateCreation,
															Date rqDateRemove,
															Date rqLimitTimePromotion,
															Date rqLimitTimeTakePromotion,
															Long rqQuantityAvailable,
															Boolean rqIsCumulative,
															List<String> rqListTags);
		public List<BaseProduct> rechercherProduitSurMotsCles(List<String> keyWords);
}
