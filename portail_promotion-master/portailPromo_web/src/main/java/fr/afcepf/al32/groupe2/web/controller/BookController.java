package fr.afcepf.al32.groupe2.web.controller;

import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import fr.afcepf.al32.groupe2.entity.Client;
import fr.afcepf.al32.groupe2.entity.Reservation;
import fr.afcepf.al32.groupe2.entity.ReservationProduct;
import fr.afcepf.al32.groupe2.service.IServicePromotion;
import fr.afcepf.al32.groupe2.service.IServiceReservation;
import fr.afcepf.al32.groupe2.service.impl.EmailWServiceImpl;

@Component
@Transactional
@RequestScope
@RequestMapping("/api/bookcontroller")
public class BookController {
	
	@Autowired
	ConnectionController connectionController;
	
	@Autowired
	IServiceReservation reservationService;

	 @Autowired
    EmailWServiceImpl emailService;
    // bnm - 14/05/2019 impl�mentation d'un web service d'envoi d'email
    // private EmailService emailService;
	
	@Autowired
	IServicePromotion promotionService; 
	
	private Double quantityBooked;
	
	@PostMapping("/book/{id}/{quantitybooked}")
	public String book() {
		
		Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	  String promo = params.get("promotion");
	  Long id = Long.parseLong(promo);
	  
		Reservation reservation = new Reservation();
		ReservationProduct reservationProduct = new ReservationProduct();
	    reservationProduct.setPromotion(promotionService.recherchePromotionParIdentifiant(id));
		reservationProduct.setQuantityRequested(Math.min(quantityBooked, promotionService.recherchePromotionParIdentifiant(id).getQuantityRemaining()));
		
		reservation.setClient((Client)connectionController.getLoggedUser());
		reservation.setDateCreation(new Date());

		long withDrawalCode = Math.round(Math.random() * 100000);

		reservation.setWithdrawalCode(String.valueOf(withDrawalCode));
		reservation.setReservationProduct(reservationProduct);
		
		reservationService.ajouterReservation(reservation);

		promotionService.recherchePromotionParIdentifiant(id).decreaseAvailableQuantity(quantityBooked);

		emailService.sendEmailReservation((Client) connectionController.getLoggedUser(), reservation);
		
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
