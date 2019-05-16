package fr.afcepf.al33.bnm.wsemail;

public class wsEmailResponseDTO {
        // Attributs
        private int codeRetour;
        private String reponse ;
        private Exception erreur  ;
        
        public int getCodeRetour() {
            return this.codeRetour;
        }
        public void setCodeRetour(int codeRetour) {
            this.codeRetour = codeRetour;
        }
        // Getters & Setters
        public Exception getErreur() {
            return this.erreur;
        }
        public void setErreur(Exception error) {
            this.erreur = error ;
        }
        public String getReponse() {
            return this.reponse;
        }
        public void setReponse(String answer) {
            this.reponse = answer;
        }
        
        @Override
        public String toString() { 
                 String chaine = " OBJET    [ wsEmailResponseDTO ]  \n  " ;
                 chaine += ( "Code Retour  [ " + String.valueOf(this.codeRetour) + " ] \n") ;
                 chaine += ( "Reponse [ " + this.reponse + " ]\n") ;
                 if ( this.erreur != null ) {
                     chaine += ( "Exception GetMessage [ " +  this.erreur.getMessage() + " ] \n") ;
                     chaine += ( "Exception GetCause [ " +  this.erreur.getCause() + " ] \n") ;
                     chaine += ( "Exception GetStackTrace [ " +  this.erreur.getStackTrace() + " ] \n") ;
                 }
            return chaine ;
        }     
     
}
