package fr.afcepf.al33.wsauthentification.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import fr.afcepf.al33.wsauthentification.dao.itf.UserDao;
import fr.afcepf.al33.wsauthentification.entity.User;

@Component
@Transactional
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public User findOneById(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public User findOneByLoginAndPassword(String login, String password) {
		try {
			return em.createNamedQuery("User.findByLoginAndPassword", User.class).setParameter("login", login).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}

}
