package ma.enset.delibrations.exceptions;

public class RegleSemestreNotFoundException extends Exception {
    public RegleSemestreNotFoundException(Long id) {
        super("Regle Semestre with id " + id + " not found");
    }
}
