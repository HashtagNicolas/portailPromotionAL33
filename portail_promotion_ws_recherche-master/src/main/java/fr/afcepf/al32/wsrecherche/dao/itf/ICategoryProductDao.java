package fr.afcepf.al32.wsrecherche.dao.itf;

import java.util.List;

import fr.afcepf.al32.wsrecherche.entity.CategoryProduct;	
	


public interface ICategoryProductDao {
	CategoryProduct findOne(Long idcategorie);
	
	List<CategoryProduct> findAllRootCategories();

	List<CategoryProduct> findAllRootCategoriesWithDaughters();
	

}
