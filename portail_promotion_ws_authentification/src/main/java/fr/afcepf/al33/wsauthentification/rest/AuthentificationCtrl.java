package fr.afcepf.al33.wsauthentification.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al33.wsauthentification.service.itf.IAuthenticationService;

@RestController
@RequestMapping(value = "/rest/auth", headers = "Accept=application/json")
public class AuthentificationCtrl {

	@Autowired
	private IAuthenticationService authenticationService;

	@GetMapping("/login")
	public Long getUserIdByLoginAndPassword(@RequestParam String login, @RequestParam String password) {

		return authenticationService.getUserIdByLoginAndPassword(login, password);
	}
}
