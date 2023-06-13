package ma.enset.delibrations.entities.exceptions;

public class AnneeUnivNotFoundException extends Exception {
    public AnneeUnivNotFoundException(Long id) {
        super("AnneeUniv with id " + id + " not found");
    }
}


