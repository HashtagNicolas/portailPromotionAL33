package fr.afcepf.al32.groupe2.ws.itf;

import fr.afcepf.al33.ws.dto.UserDTO;

public interface IWsAuthentification {

	UserDTO getLoggedUser(String login, String password);
}
