package fr.afcepf.al32.groupe2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al32.groupe2.entity.Promotion;
import fr.afcepf.al32.groupe2.service.IServicePromotion;


//exemple url postman pour la class ConnectionController : 
//http://localhost:8081/api/auth/connexion/root1/root
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PromotionController {

	@Autowired
	private IServicePromotion promotionService;
	
	@GetMapping("/promotion/{idPromotion}")
	public Promotion connect(@PathVariable Long idPromotion) {
		
		return promotionService.recherchePromotionParIdentifiant(idPromotion);
	}

}
