package fr.afcepf.al33.wsauthentification.dao.itf;

import fr.afcepf.al33.wsauthentification.entity.User;

public interface UserDao {
	User findOneById(Long id);
	
	User findOneByLoginAndPassword(String login, String password);
}
