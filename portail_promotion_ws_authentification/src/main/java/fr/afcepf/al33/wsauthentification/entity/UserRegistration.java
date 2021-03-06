package fr.afcepf.al33.wsauthentification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import fr.afcepf.al33.wsauthentification.util.UserDeregistrationMotive;

@Entity
@Table(name="user_registration")
public class UserRegistration extends Registration {
	
	@Enumerated(EnumType.STRING)
	@Column(name="deregistration_motive")
	private UserDeregistrationMotive deregistrationMotive;

	public UserDeregistrationMotive getDeregistrationMotive() {
		return deregistrationMotive;
	}

	public void setDeregistrationMotive(UserDeregistrationMotive deregistrationMotive) {
		this.deregistrationMotive = deregistrationMotive;
	}
	
	
}
