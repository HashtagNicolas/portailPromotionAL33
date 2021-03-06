package fr.afcepf.al32.groupe2.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al32.groupe2.entity.Client;
import fr.afcepf.al32.groupe2.entity.ReferenceProduct;
import fr.afcepf.al32.groupe2.entity.Reservation;
import fr.afcepf.al32.groupe2.entity.ReservationProduct;
import fr.afcepf.al32.groupe2.service.IAuthenticationService;
import fr.afcepf.al32.groupe2.service.IServicePromotion;
import fr.afcepf.al32.groupe2.service.IServiceReservation;
import fr.afcepf.al32.groupe2.service.impl.EmailWServiceImpl;
import fr.afcepf.al32.groupe2.web.connexion.ConnectionBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

////exemple url postman pour la class BookController : 
// http://localhost:8081/api/bookcontroller/book/15/1
@Component
@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestScope
@RequestMapping("/api/bookcontroller")
@Api(value = "PortailPromotion" , description = "Gestion des Reservations")
@SessionScope
public class BookController {
	
	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	IServiceReservation reservationService;

	 @Autowired
    EmailWServiceImpl emailService;
    // bnm - 14/05/2019 impl�mentation d'un web service d'envoi d'email
    // private EmailService emailService;
	
	@Autowired
	IServicePromotion promotionService; 
	
	//TODO coté angular il faudra récupérer l'id de la promotion réservé et la quantité réservé
	@ApiOperation(value = "créer une reservation", response = List.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Liste créer"),
	        @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la ressource"),
	        @ApiResponse(code = 403, message = "Accéder à la ressource que vous essayiez d'atteindre est interdit"),
	        @ApiResponse(code = 404, message = "La ressource que vous essayiez d'atteindre n'a pas été trouvée")
	})
	@PostMapping("/book/{id}/{quantityBooked}/{idClient}")
	public void book(@PathVariable Long id, @PathVariable Double quantityBooked, @PathVariable Long idClient) {
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
		

		reservation.setClient((Client)authenticationService.findOneById(idClient));
		reservation.setDateCreation(new Date());

		long withDrawalCode = Math.round(Math.random() * 100000);

		reservation.setWithdrawalCode(String.valueOf(withDrawalCode));
		reservation.setReservationProduct(reservationProduct);
		
		reservationService.ajouterReservation(reservation);
		System.err.println(id + " ----222------ " + quantityBooked);
		promotionService.recherchePromotionParIdentifiant(id).decreaseAvailableQuantity(quantityBooked);
		System.err.println(id + " ---------- " + quantityBooked);
		//TODO Probleme d'envoie de mail : peut etre fix email envoi
		emailService.sendEmailReservation((Client) authenticationService.findOneById(idClient), reservation);
		
		quantityBooked = 1d;
		
		//return promotionService.recherchePromotionParIdentifiant(id).decreaseAvailableQuantity(quantityBooked);
	}
	

}
