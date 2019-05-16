package fr.afcepf.al32.groupe2.service;

import fr.afcepf.al32.groupe2.entity.Client;
import fr.afcepf.al32.groupe2.entity.IFollowableElement;
import fr.afcepf.al32.groupe2.entity.Promotion;
import fr.afcepf.al32.groupe2.entity.Reservation;
import fr.afcepf.al32.groupe2.entity.Shopkeeper;

public interface EmailService {
	
    // Envoi notification de création d'une promotion - bnm 15/05/2019
    void sendEmailPromoOwner(Shopkeeper destinataire, Promotion element);

    void sendEmailReservation(Client destinataire, Reservation reservation);

    void sendEmailPromotion(Client destinataire, IFollowableElement element);
}
