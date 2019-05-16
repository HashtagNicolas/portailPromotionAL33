package fr.afcepf.al32.groupe2.service.decorator;

import java.util.Date;

import fr.afcepf.al32.groupe2.entity.BaseProduct;

public class BaseProductDecorator implements IbaseProductDecorator {
	
	private Double initPrice;
	private String description;
	private String image;
	private Date addDate;
	private Date removeDate;
	
	private static BaseProductDecorator baseProductDecorator;
	
	public static BaseProductDecorator getBaseProduct() {
		
		if (baseProductDecorator == null) {
			baseProductDecorator = new BaseProductDecorator();
		}
		return baseProductDecorator;
	}
	public Double getInitPrice() {
		return initPrice;
	}
	public void setInitPrice(Double initPrice) {
		this.initPrice = initPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	@Override
	public IbaseProductDecorator getInitPrice(Double initPrice) {
		this.initPrice = initPrice;
		return this;
	}
	@Override
	public IbaseProductDecorator getDescription(String description) {
		this.description = description;
		return this;
	}
	@Override
	public IbaseProductDecorator getImage(String image) {
		this.image = image;
		return this;
	}
	@Override
	public IbaseProductDecorator getAddDate(Date addDate) {
		this.addDate = addDate;
		return this;
	}
	@Override
	public IbaseProductDecorator getRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
		return this;
	}
	
	@Override
	public Long createProduct(Long id) {
		BaseProduct baseProduct = new BaseProduct();
		baseProduct.setInitPrice(initPrice);
		baseProduct.setDescription(description);
		baseProduct.setImage(image);
		baseProduct.setAddDate(addDate);
		baseProduct.setRemoveDate(removeDate);
		
		return id;	
	}
}
