package fr.afcepf.al32.groupe2.web.delegate;

import java.io.InputStream;
import java.util.Properties;
import java.util.StringJoiner;

import org.springframework.web.client.RestTemplate;

import fr.afcepf.al32.groupe2.ws.itf.IWsAuthentification;
import fr.afcepf.al33.ws.dto.UserDTO;

public class WsAuthentificationImpl implements IWsAuthentification {

	private RestTemplate restTemplate;
	private String base_url;

	public WsAuthentificationImpl() {
		restTemplate = new RestTemplate();
		try {
			Properties props = new Properties();
			InputStream inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("web_services.properties");
			props.load(inputStream);
			inputStream.close();

			this.base_url = props.getProperty("ws_authentification.base_url");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserDTO getLoggedUser(String login, String password) {
		StringJoiner keywordsJoiner = new StringJoiner(",");
		String url = String.format("%s?login=%s", base_url, login, keywordsJoiner.toString());
		return restTemplate.getForObject(url, UserDTO.class);
	}

}
