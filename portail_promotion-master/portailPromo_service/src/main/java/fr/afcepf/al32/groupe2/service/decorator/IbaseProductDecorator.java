package fr.afcepf.al32.groupe2.service.decorator;

import java.util.Date;

public interface IbaseProductDecorator extends IproductDecorator {
	
	IbaseProductDecorator getInitPrice(Double initPrice);
	IbaseProductDecorator getDescription(String description);
	IbaseProductDecorator getImage(String image);
	IbaseProductDecorator getAddDate(Date addDate);
	IbaseProductDecorator getRemoveDate(Date removeDate);
	
}
