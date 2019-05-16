package fr.afcepf.al32.groupe2.service.decorator;

public class ProductDecorator implements IproductDecorator{ 
	
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Override
	public Long createProduct(Long id) {
		
		return id;
		
	}
	
	@Override
	public String toString() {
		return "ProductDecorator [id=" + id + ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
