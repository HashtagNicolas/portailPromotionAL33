package fr.afcepf.al32.groupe2.web.controller;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
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
	

	
	//TODO coté angular il faudra récupérer l'id de la promotion réservé et la quantité réservé
	@PostMapping("/book/{id}/{quantityBooked}")
	public void book(@PathVariable Long id, @PathVariable Double quantityBooked) {
		System.err.println(id + " ----debut methode------ " + quantityBooked);
//		//recupère l'id de la promotion réservé dans WEB-INF/components/promotion3 line:314-319
//		Map<String,String> params = 
//                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//	  String promo = params.get("promotion");
//	  Long id = Long.parseLong(promo);
	  
		Reservation reservation = new Reservation();
		ReservationProduct reservationProduct = new ReservationProduct();
	    reservationProduct.setPromotion(promotionService.recherchePromotionParIdentifiant(id));
		try {
			reservationProduct.setQuantityRequested(Math.min(quantityBooked, promotionService.recherchePromotionParIdentifiant(id).getQuantityRemaining()));
		} catch (Exception e) {
			System.err.println("L'id promotion : " + id +" n'existe pas");
		}
		

		reservation.setClient((Client)connectionController.getLoggedUser());
		reservation.setDateCreation(new Date());

		long withDrawalCode = Math.round(Math.random() * 100000);

		reservation.setWithdrawalCode(String.valueOf(withDrawalCode));
		reservation.setReservationProduct(reservationProduct);
		
		reservationService.ajouterReservation(reservation);
		System.err.println(id + " ----222------ " + quantityBooked);
		promotionService.recherchePromotionParIdentifiant(id).decreaseAvailableQuantity(quantityBooked);
		System.err.println(id + " ---------- " + quantityBooked);
		//TODO à corriger pour l'envoie de mail
		//emailService.sendEmailReservation((Client) connectionController.getLoggedUser(), reservation);
		
		quantityBooked = 1d;
		

		//return promotionService.recherchePromotionParIdentifiant(id).decreaseAvailableQuantity(quantityBooked);

		//return "../../client/reservationClient/gererReservationClient.xhtml";
	}
	
}
