package fr.afcepf.al32.groupe2.web.productlist;

import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.transaction.Transactional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import fr.afcepf.al32.groupe2.entity.Client;
import fr.afcepf.al32.groupe2.entity.Reservation;
import fr.afcepf.al32.groupe2.entity.ReservationProduct;
import fr.afcepf.al32.groupe2.service.IServicePromotion;
import fr.afcepf.al32.groupe2.service.IServiceReservation;
import fr.afcepf.al32.groupe2.service.impl.EmailWServiceImpl;
import fr.afcepf.al32.groupe2.web.connexion.ConnectionBean;

@Component
@Transactional
@RequestScope
public class BookBean {
	
	@Autowired
	ConnectionBean connectionBean;
	
	@Autowired
	IServiceReservation reservationService;

	 @Autowired
    EmailWServiceImpl emailService;
    // bnm - 14/05/2019 impl�mentation d'un web service d'envoi d'email
    // private EmailService emailService;
	
	@Autowired
	IServicePromotion promotionService; 
	
	private Double quantityBooked;
	
	public String book() {
		
		//recupère l'id de la promotion réservé dans WEB-INF/components/promotion3 line:314-319
		Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	  String promo = params.get("promotion");
	  Long id = Long.parseLong(promo);
	  
		Reservation reservation = new Reservation();
		ReservationProduct reservationProduct = new ReservationProduct();
	    reservationProduct.setPromotion(promotionService.recherchePromotionParIdentifiant(id));
		reservationProduct.setQuantityRequested(Math.min(quantityBooked, promotionService.recherchePromotionParIdentifiant(id).getQuantityRemaining()));
		
		reservation.setClient((Client)connectionBean.getLoggedUser());
		reservation.setDateCreation(new Date());

		long withDrawalCode = Math.round(Math.random() * 100000);

		reservation.setWithdrawalCode(String.valueOf(withDrawalCode));
		reservation.setReservationProduct(reservationProduct);
		
		reservationService.ajouterReservation(reservation);

		promotionService.recherchePromotionParIdentifiant(id).decreaseAvailableQuantity(quantityBooked);

		emailService.sendEmailReservation((Client) connectionBean.getLoggedUser(), reservation);
		
		quantityBooked = 1d;
		
		return "../../client/reservationClient/gererReservationClient.xhtml";
	}

	public Double getQuantityBooked() {
		return quantityBooked;
	}

	public void setQuantityBooked(Double quantityBooked) {
		this.quantityBooked = quantityBooked;
	}
	
	
}
