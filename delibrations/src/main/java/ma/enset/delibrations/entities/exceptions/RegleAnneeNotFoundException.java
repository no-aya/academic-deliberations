package ma.enset.delibrations.entities.exceptions;

public class RegleAnneeNotFoundException extends Exception {
    public RegleAnneeNotFoundException(Long id) {
        super("Regle Annee with id " + id + " not found");
    }
}
