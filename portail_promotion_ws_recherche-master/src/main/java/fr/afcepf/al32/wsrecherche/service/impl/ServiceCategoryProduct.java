package fr.afcepf.al32.wsrecherche.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afcepf.al32.wsrecherche.dao.itf.ICategoryProductDao;
import fr.afcepf.al32.wsrecherche.entity.CategoryProduct;
import fr.afcepf.al32.wsrecherche.service.itf.IServiceCategoryProduct;

@Transactional
@Component
public class ServiceCategoryProduct implements IServiceCategoryProduct {
	@Autowired
	private ICategoryProductDao categoryProductDao;
	
	public CategoryProduct rechercheCategoryProduitParIdentifiant(Long id) {
		                  
		
			
			return categoryProductDao.findOne(id);
	}

	@Override
	public List<CategoryProduct> getAllRootCategories() {
		return categoryProductDao.findAllRootCategories();
	}

	@Override
	public List<CategoryProduct> getAllRootCategoriesWithDaughters() {
		return categoryProductDao.findAllRootCategoriesWithDaughters();
	}

}
