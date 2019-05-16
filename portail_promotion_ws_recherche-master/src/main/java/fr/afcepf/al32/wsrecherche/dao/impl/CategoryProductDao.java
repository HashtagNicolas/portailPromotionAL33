package fr.afcepf.al32.wsrecherche.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al32.wsrecherche.dao.itf.ICategoryProductDao;
import fr.afcepf.al32.wsrecherche.entity.CategoryProduct;

@Transactional
@Component
public class CategoryProductDao implements ICategoryProductDao{
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public CategoryProduct findOne(Long idcategorie) {
		
		return entityManager.find(CategoryProduct.class, idcategorie);
	}


	@Override
	public List<CategoryProduct> findAllRootCategories() {
		return entityManager.createNamedQuery("CategoryProduct.findAllRootCategories", CategoryProduct.class).getResultList();
	}


	@Override
	public List<CategoryProduct> findAllRootCategoriesWithDaughters() {
		return entityManager.createNamedQuery("CategoryProduct.findAllRootCategoriesWithDaughter", CategoryProduct.class).getResultList();
	}

}
