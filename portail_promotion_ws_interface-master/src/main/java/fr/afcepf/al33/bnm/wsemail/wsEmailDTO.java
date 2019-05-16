package fr.afcepf.al33.bnm.wsemail;

import org.springframework.core.io.ClassPathResource;

public class wsEmailDTO {

    private String listeDestinataires[] ;
    private String texteMessage ;
    private String sujetMessage ;
    private ClassPathResource pieceJointe ;
    private String titrePieceJointe ;
    
    public wsEmailDTO() { }

    public wsEmailDTO( String[] listeDestinataires, String texteMessage, String sujetMessage, ClassPathResource pieceJointe, String titrePieceJointe ) {
        this.listeDestinataires = listeDestinataires ;
        this.texteMessage = texteMessage ;
        this.sujetMessage = sujetMessage ;
        this.pieceJointe = pieceJointe ;
        this.titrePieceJointe  = titrePieceJointe ;
    }

    public String[] getListeDestinataires() {
        return listeDestinataires;
    }
    
    public void setListeDestinataires( String[] listeDestinataires) {
        this.listeDestinataires = listeDestinataires;
    }
    
    public String getTexteMessage() {
        return texteMessage;
    }
    
    public void setTexteMessage(String texteMessage) {
        this.texteMessage = texteMessage;
    }
    
    public String getSujetMessage() {
        return sujetMessage;
    }
    
    public void setSujetMessage(String sujetMessage) {
        this.sujetMessage = sujetMessage;
    }
    
    public ClassPathResource getPieceJointe() {
        return pieceJointe;
    }
    
    public void setPieceJointe(ClassPathResource pieceJointe) {
        this.pieceJointe = pieceJointe;
    }
    
    public String getTitrePieceJointe() {
        return titrePieceJointe;
    }
    
    public void setTitrePieceJointe(String titrePieceJointe) {
        this.titrePieceJointe = titrePieceJointe;
    }
    
    @Override
    public String toString() { 
             String chaine = " OBJET    [ wsMailDTO ]  \n  " ;
             chaine += " Destinataires : [  \n" ;
             for (String destinataire : this.listeDestinataires)  chaine +=  (destinataire + "\n")  ;
             chaine += " ] \n";
             chaine += ( "Sujet  [ " + this.sujetMessage + " ] \n") ;
             chaine += ( "Pièce Jointe " + this.titrePieceJointe + " ]\n") ;
             chaine += ( "Message [ " + this.texteMessage + " ] \n") ;
        return chaine ;
    }
}
