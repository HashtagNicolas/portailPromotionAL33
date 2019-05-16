package fr.afcepf.al33.wsauthentification.service.itf;

public interface IAuthenticationService {

	public Long getUserIdByLoginAndPassword(String login, String password);

}
