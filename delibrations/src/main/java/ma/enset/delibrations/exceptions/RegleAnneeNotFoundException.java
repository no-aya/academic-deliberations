package ma.enset.delibrations.exceptions;

public class RegleAnneeNotFoundException extends Exception {
    public RegleAnneeNotFoundException(Long id) {
        super("Regle Annee with id " + id + " not found");
    }
}
