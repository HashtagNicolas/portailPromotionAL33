package fr.afcepf.al33.bnm.wsemail.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.al33.bnm.wsemail.wsEmailDTO;
import fr.afcepf.al33.bnm.wsemail.wsEmailResponseDTO;

@RestController
public class EmailController {

    // Déclaration du  sender JavaMail    
    @Autowired
	private JavaMailSender sender;

//    @Autowired 
    private wsEmailDTO courriel  ;

    private wsEmailResponseDTO resultat;
    
    private static final  String  MESSAGE_RETOUR =  "ENVOI DU COURRIEL";   
    private static final String SUCCESS_STATUS = "OK";
    private static final String ERROR_STATUS = "KO";
    private static final int    SUCCESS_CODE =  0;
    private static final int   ERROR_SCODE   =  1;

    
//  @RequestMapping("/envoi")
    @RequestMapping(value="/envoi", headers="Accept=application/json", method = RequestMethod.POST)
	public wsEmailResponseDTO sendMail( @RequestBody wsEmailDTO emailToSend ) {
	    
    //    //String listeDestinataires[] = { "portailpromo33@mailo.com", "portailpromotional33@gmail.com" };    
        
    //String listeDestinataire[] = { "portailpromo33@mailo.com" };
    //  wsEmailDTO courriel = new wsEmailDTO();
    //  courriel.setListeDestinataires( listeDestinataires);
    //  courriel.setPieceJointe(new ClassPathResource("coup_de_pied.jpg"));
    //  courriel.setSujetMessage("Courriel de test Portail promotion / bnm");
    //  courriel.setTexteMessage("Message portail_promotion_ws_email   envoyé par  un  Web Service Rest");
    //  courriel.setTitrePieceJointe("coup_de_pied.jpg");
        
        // Initialisation du résultat du traitement
        resultat = new wsEmailResponseDTO();
        
        // Récupération de l'objet courriel à traiter  
       courriel = emailToSend ;
       courriel.toString();
       
        // Envoi des emails & de gestion des retours. 
		try {
	        // Préparation de la structure d'envoi du courriel
	        MimeMessage Enveloppe = sender.createMimeMessage();
	        MimeMessageHelper helper;
	        //Si Pas de pièce jointe
	        if( courriel.getTitrePieceJointe() == null ) 
	            // message simple 
	            helper = new MimeMessageHelper( Enveloppe, false, "utf-8" );
	        else {
	            // true permet d'acter un format de message multiparties
	            helper = new MimeMessageHelper( Enveloppe, true, "utf-8"  );
	            helper.addAttachment( courriel.getTitrePieceJointe(), courriel.getPieceJointe() );
	        }
	        // Préparation de l'envoi
			helper.setTo( courriel.getListeDestinataires() );
			helper.setSubject(  courriel.getSujetMessage() );
			helper.setText( courriel.getTexteMessage(), true ); //Flag true =>  "text/html"
            // Enveloppe.setContent( courriel.getTexteMessage(), "text/html" ); */
			// Envoi du courriel
			sender.send( Enveloppe );
			resultat.setCodeRetour(SUCCESS_CODE);
	         resultat.setReponse( MESSAGE_RETOUR + " [ " + SUCCESS_STATUS + " ] ");
		} 
		catch ( Exception erreur ) {
		    resultat.setCodeRetour(ERROR_SCODE);
		    resultat.setReponse( MESSAGE_RETOUR + " [ " + ERROR_STATUS +" ] ");
		    resultat.setErreur(erreur);
		}  
		 return resultat;
	}		
}
