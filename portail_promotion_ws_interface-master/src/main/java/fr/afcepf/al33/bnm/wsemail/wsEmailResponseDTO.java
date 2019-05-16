package fr.afcepf.al33.bnm.wsemail;

public class wsEmailResponseDTO {
        private String Answer ;
        private Exception error  ;
        
        public Exception getError() {
            return error;
        }
        public void setError(Exception error) {
            this.error = error;
        }
        public String getAnswer() {
            return Answer;
        }
        public void setAnswer(String answer) {
            Answer = answer;
        }
}
