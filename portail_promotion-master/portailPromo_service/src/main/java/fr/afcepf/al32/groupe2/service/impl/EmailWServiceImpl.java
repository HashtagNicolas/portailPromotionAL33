package fr.afcepf.al32.groupe2.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.afcepf.al32.groupe2.entity.Client;
import fr.afcepf.al32.groupe2.entity.IFollowableElement;
import fr.afcepf.al32.groupe2.entity.Promotion;
import fr.afcepf.al32.groupe2.entity.Reservation;
import fr.afcepf.al32.groupe2.entity.Shopkeeper;
import fr.afcepf.al32.groupe2.service.EmailService;
import fr.afcepf.al33.bnm.wsemail.wsEmailDTO;
import fr.afcepf.al33.bnm.wsemail.wsEmailResponseDTO;

@Component
public class EmailWServiceImpl implements EmailService {
	
    private static final String URL_WEB_SERVICE_ENVOI_EMAIL = "http://localhost:8090/portail_promotion_servicemail/envoi";

    private static final String TITRE_MAIL_RESERVATION = "Confirmation de réservation" ;
    private static final String TITRE_MAIL_PROMOTION = "Promotion ajoutée" ;
    private static final String TITRE_MAIL_ALERTE_RESERVATION = "Nouvelle réservation " ;

    private static final String SUJET_MAIL_RESERVATION = "Confirmation de réservation" ;
    private static final String SUJET_MAIL_ALERTE_RESERVATION = "Nouvelle réservation sur Promo32 " ;
    private static final String SUJET_MAIL_PROMOTION = "Nouvelle promotion" ;

    
    
    @Override
	public void sendEmailPromoOwner(Shopkeeper destinataire, Promotion element) {
        // Création de l'objet attendu par le web service email
        wsEmailDTO courriel = new wsEmailDTO();
        courriel.setTexteMessage( getEmailForNewPromotion(destinataire, element ) );
        String ListeDestinataires[] = { destinataire.getEmail(), "portailpromo33@mailo.com", "portailpromotional33@gmail.com"} ;
        courriel.setListeDestinataires( ListeDestinataires );
        courriel.setSujetMessage(SUJET_MAIL_PROMOTION);
        // Appel du webservice d'envoi de courriels
        wsEmailResponseDTO resultatEnvoi = new wsEmailResponseDTO();
        RestTemplate restTemplate = new RestTemplate();
        resultatEnvoi = restTemplate.postForObject( URL_WEB_SERVICE_ENVOI_EMAIL, courriel, wsEmailResponseDTO.class );
        // TODO gestion du résultat de l'envoi
        
        //TODO DEBUG objet DTO email et réponse
        System.out.println(resultatEnvoi.toString());
	}

	@Override
	public void sendEmailReservation(Client destinataire, Reservation reservation) {
	    //                            ENVOI  emails clients
	    // Création de l'objet email attendu par le web service email
	    wsEmailDTO courrielClient = new wsEmailDTO();
	    courrielClient.setTexteMessage( getEmailForReservationConfirmation(destinataire, reservation) );
	    String ListeDestinataires[] = { destinataire.getEmail(), "portailpromo33@mailo.com"} ;
	    courrielClient.setListeDestinataires( ListeDestinataires );
	    courrielClient.setSujetMessage(SUJET_MAIL_RESERVATION);
	    // Appel du webservice d'envoi de courriels
	    wsEmailResponseDTO resultatEnvoiClient = new wsEmailResponseDTO();
	    RestTemplate restTemplate = new RestTemplate();
	    resultatEnvoiClient = restTemplate.postForObject( URL_WEB_SERVICE_ENVOI_EMAIL, courrielClient, wsEmailResponseDTO.class );
	    // TODO gestion du résultat de l'envoi 2

        //TODO DEBUG objet DTO email et réponse
        System.out.println(resultatEnvoiClient.toString());
    
	    
	    //                           ENVOI  emails commerçant
        // Création de l'objet email attendu par le web service email
	    wsEmailDTO courrielVendeur = new wsEmailDTO();
        courrielVendeur.setTexteMessage( getEmailForReservationAlert(reservation) );
        String ListeVendeurs[] = { reservation.getReservationProduct().getPromotion().getShopList().get(0).getOwner().getEmail(), "portailpromo33@mailo.com"} ;
        courrielVendeur.setListeDestinataires( ListeVendeurs );
        courrielVendeur.setSujetMessage(SUJET_MAIL_ALERTE_RESERVATION);
        // Appel du webservice d'envoi de courriels
        wsEmailResponseDTO resultatEnvoiVendeur = new wsEmailResponseDTO();
        RestTemplate restTemplateV= new RestTemplate();
        resultatEnvoiVendeur = restTemplateV.postForObject( URL_WEB_SERVICE_ENVOI_EMAIL, courrielVendeur, wsEmailResponseDTO.class );
        // TODO gestion du résultat de l'envoi 3

        //TODO DEBUG objet DTO email et réponse
        System.out.println(resultatEnvoiVendeur.toString());
	
	}

    private String getEmailForReservationAlert( Reservation reservation ){
        String template = getEmailTemplate( ) +
                "\n" +
                "                        <h1> " + TITRE_MAIL_ALERTE_RESERVATION + " </h1>\n" +
                "\n" +
                "                    </td>\n" +
                 "                </tr>\n" +
                 "                <tr>\n" +
                 "                    <td class=\"content\">\n" +
                 "\n" +
                 "                        <h2>Bonjour %s </h2>\n" +
                 "\n" +
                 "                        <p>Félicitations... Vous avez une nouvelle réservation de produit sur Promos 32 </p> </br>\n" +
                 "                        <p> CARACTÉRISTIQUES DE LA RÉSERVATION :  </p>\n" +
                 "                                    <ul>  \n" +
                 "                                               <li>  Promotion concernée :  %s </li> \n" + 
                 "                                               <li>  Produit concerné :  %s </li> \n" + 
                 "                                               <li>  Quantité réservée :  %s </li> \n" + 
                 "                                               <li>  Date de la réservation :  %s </li> \n" + 
                 "                                   </ul> \n" +
                 "                    </td>\n" +
                 "                </tr>\n" +
                 "            </table>\n" +
                 "\n" +
                 "        </td>\n" +
                 "    </tr>\n" +
                 "</table>\n" +
                 "</body>\n" +
                 "</html>";
         return String.format( template, reservation.getReservationProduct().getPromotion().getShopList().get(0).getOwner().getFirstName(),
                                                   reservation.getReservationProduct().getPromotion().getName(), 
                                                   reservation.getReservationProduct().getPromotion().getBaseProduct().getReferenceProduct().getName(),
                                                   reservation.getReservationProduct().getQuantityRequested().intValue(),
                                                   reservation.getDateCreation().toLocaleString() );
  }

	
	   private String getEmailForNewPromotion(Shopkeeper destinataire, Promotion promotion ){
	       String template = getEmailTemplate( ) +
	               "\n" +
	               "                        <h1> " + TITRE_MAIL_PROMOTION + " </h1>\n" +
	               "\n" +
	               "                    </td>\n" +
	                "                </tr>\n" +
	                "                <tr>\n" +
	                "                    <td class=\"content\">\n" +
	                "\n" +
	                "                        <h2>Bonjour %s </h2>\n" +
	                "\n" +
	                "                        <p>Félicitations... Votre offre promotionnelle  <b> %s </b>  est désormais disponible sur Promos 32 </p> </br>\n" +
	                "                        <p>CARACTÉRISTIQUES DE VOTRE OFFRE :  </p>\n" +
	                "                                    <ul>  \n" +
	                "                                               <li>  Description :  %s </li> \n" + 
	                "                                               <li>  Début de la promotion le :  %s </li> \n" + 
	                "                                               <li>  Fin de la promotion le       :  %s </li> \n" + 
	                "                                   </ul> \n" +
	                "                    </td>\n" +
	                "                </tr>\n" +
	                "            </table>\n" +
	                "\n" +
	                "        </td>\n" +
	                "    </tr>\n" +
	                "</table>\n" +
	                "</body>\n" +
	                "</html>";
	        return String.format(template, destinataire.getFirstName(), promotion.getName(), promotion.getDescription(), promotion.getDateCreation().toLocaleString(),
	                                                 promotion.getEndDate().toLocaleString() );
	    }

	
	private String getEmailForReservationConfirmation(Client destinataire, Reservation reservation){
	  String template = getEmailTemplate() +
              "\n" +
              "                        <h1> " + TITRE_MAIL_RESERVATION + " </h1>\n" +
              "\n" +
	          "                    </td>\n" +
	                "                </tr>\n" +
	                "                <tr>\n" +
	                "                    <td class=\"content\">\n" +
	                "\n" +
	                "                        <h2>Bonjour %s,</h2>\n" +
	                "\n" +
	                "                        <p>Promo 32 vous confirme la réservation <b> n° %d du %tD </b> concernant le produit <b> %s </b>. \n </br> " +
	                " Votre code de retrait est <b>%s</b>.</p>\n" +
	                "\n" +
	                "                    </td>\n" +
	                "                </tr>\n" +
	                "            </table>\n" +
	                "\n" +
	                "        </td>\n" +
	                "    </tr>\n" +
	                "</table>\n" +
	                "</body>\n" +
	                "</html>";
	        return String.format(template, destinataire.getFirstName(), reservation.getId(), reservation.getDateCreation(), reservation.getReservationProduct().getPromotion().getBaseProduct().getReferenceProduct().getName(), reservation.getWithdrawalCode());
	    }

	
	private String getEmailTemplate( ){
	       String template = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
	                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
	                "<head>\n" +
	                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
	                "    <meta name=\"viewport\" content=\"width=device-width\"/>\n" +
	                "\n" +
	                "    <!-- For development, pass document through inliner -->\n" +
	                "    <link rel=\"stylesheet\" href=\"css/simple.css\">\n" +
	                "\n" +
	                "    <style type=\"text/css\">\n" +
	                "    * { margin: 0; padding: 0; font-size: 100%%; font-family: 'Avenir Next', \"Helvetica Neue\", \"Helvetica\", Helvetica, Arial, sans-serif; line-height: 1.65; }\n" +
	                "\n" +
	                "img { max-width: 100%%; margin: 0 auto; display: block; }\n" +
	                "\n" +
	                "body, .body-wrap { width: 100%% !important; height: 100%%; background: #f8f8f8; }\n" +
	                "\n" +
	                "a { color: #71bc37; text-decoration: none; }\n" +
	                "\n" +
	                "a:hover { text-decoration: underline; }\n" +
	                "\n" +
	                ".text-center { text-align: center; }\n" +
	                "\n" +
	                ".text-right { text-align: right; }\n" +
	                "\n" +
	                ".text-left { text-align: left; }\n" +
	                "\n" +
	                ".button { display: inline-block; color: white; background: #71bc37; border: solid #71bc37; border-width: 10px 20px 8px; font-weight: bold; border-radius: 4px; }\n" +
	                "\n" +
	                ".button:hover { text-decoration: none; }\n" +
	                "\n" +
	                "h1, h2, h3, h4, h5, h6 { margin-bottom: 20px; line-height: 1.25; }\n" +
	                "\n" +
	                "h1 { font-size: 32px; }\n" +
	                "\n" +
	                "h2 { font-size: 28px; }\n" +
	                "\n" +
	                "h3 { font-size: 24px; }\n" +
	                "\n" +
	                "h4 { font-size: 20px; }\n" +
	                "\n" +
	                "h5 { font-size: 16px; }\n" +
	                "\n" +
	                "p, ul, ol { font-size: 16px; font-weight: normal; margin-bottom: 20px; }\n" +
	                "\n" +
	                ".container { display: block !important; clear: both !important; margin: 0 auto !important; max-width: 580px !important; }\n" +
	                "\n" +
	                ".container table { width: 100%% !important; border-collapse: collapse; }\n" +
	                "\n" +
	                ".container .masthead { padding: 80px 0; background: #008080; color: white; }\n" +
	                "\n" +
	                ".container .masthead h1 { margin: 0 auto !important; max-width: 90%%; text-transform: uppercase; }\n" +
	                "\n" +
	                ".container .content { background: white; padding: 30px 35px; }\n" +
	                "\n" +
	                ".container .content.footer { background: none; }\n" +
	                "\n" +
	                ".container .content.footer p { margin-bottom: 0; color: #888; text-align: center; font-size: 14px; }\n" +
	                "\n" +
	                ".container .content.footer a { color: #888; text-decoration: none; font-weight: bold; }\n" +
	                "\n" +
	                ".container .content.footer a:hover { text-decoration: underline; }" +
	                "    </style>\n" +
	                "</head>\n" +
	                "<body>\n" +
	                "<table class=\"body-wrap\">\n" +
	                "    <tr>\n" +
	                "        <td class=\"container\">\n" +
	                "\n" +
	                "            <!-- Message start -->\n" +
	                "            <table>\n" +
	                "                <tr>\n" +
	                "                    <td align=\"center\" class=\"masthead\">\n" ;
	        return template ;
	        
	}

    @Override
    public void sendEmailPromotion(Client destinataire, IFollowableElement element) {
        // TODO Auto-generated method stub
        
    }

}
