package ma.enset.delibrations.exceptions;

public class RegleCalculNotFoundException extends Exception {
    public RegleCalculNotFoundException(Long id) {
        super("Regle Calcul with id " + id + " not found");
    }
}
