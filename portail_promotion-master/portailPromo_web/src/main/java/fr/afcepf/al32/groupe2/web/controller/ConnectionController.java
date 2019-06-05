package fr.afcepf.al32.groupe2.web.controller;

import javax.faces.context.FacesContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al32.groupe2.entity.User;
import fr.afcepf.al32.groupe2.service.IAuthenticationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@SessionScope
@RequestMapping("/api/auth")
public class ConnectionController {

	@Autowired
	private IAuthenticationService authenticationService;

	private User loggedUser;

	private String message;
	
	@PostMapping("/connexion/{login}/{password}")
	public User connect(@PathVariable String login, @PathVariable String password) {
		//AQ1905
		// ws_authentification.base_url=http://localhost:8088/portail_promotion_ws_authentification/rest/auth
		String urlAuth = "http://localhost:8088/portail_promotion_ws_authentification/rest/auth/login" +
				"?login=" + login + "&password=" + password;
		RestTemplate restTemplate = new RestTemplate();
		Long userId;
		try {
			userId = restTemplate.getForObject(urlAuth, Long.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			userId = 0L;
		}
		User newUser = authenticationService.findOneById(userId);
	
		//AQ1905

		if(newUser != null) {
			loggedUser = newUser;
			message = null;
		} else {
			message = "Informations de connexion incorrectes";
		}
		//jsonIgnore à mettre au niveau du DTO
		return authenticationService.findOneByLoginAndPassword(login, password);
	}
	
	@GetMapping("/logout")
	public String logout() {
		loggedUser = null;

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();


		return "../../index.xhtml";
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public String getMessage() {
		return message;
	}



}
