package fr.afcepf.al32.wsrecherche.service.itf;

import java.util.List;

import fr.afcepf.al32.wsrecherche.entity.CategoryProduct;


public interface IServiceCategoryProduct {
	CategoryProduct  rechercheCategoryProduitParIdentifiant(Long id) ;
	
	List<CategoryProduct> getAllRootCategories();
	
	List<CategoryProduct> getAllRootCategoriesWithDaughters();
}
