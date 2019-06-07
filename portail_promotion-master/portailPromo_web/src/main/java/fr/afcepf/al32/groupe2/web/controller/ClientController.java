package fr.afcepf.al32.groupe2.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al32.groupe2.entity.Client;
import fr.afcepf.al32.groupe2.entity.Reservation;
import fr.afcepf.al32.groupe2.entity.User;
import fr.afcepf.al32.groupe2.service.IAuthenticationService;
import fr.afcepf.al32.groupe2.service.IServiceReservation;
import fr.afcepf.al32.groupe2.web.connexion.ConnectionBean;
import fr.afcepf.al33.ws.dto.UserDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@SessionScope
@RequestMapping("/client")
@Transactional
public class ClientController {
	
	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	private IServiceReservation serviceReservation;
	
	

	@PostMapping("/bookList/{id}")
	public List<Reservation> getBookList(@PathVariable Long id) {
		
		return serviceReservation.findAllByClient((Client) authenticationService.findOneById(id));
	}

	
	
}