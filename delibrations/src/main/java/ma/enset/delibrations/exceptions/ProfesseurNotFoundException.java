package ma.enset.delibrations.exceptions;

public class ProfesseurNotFoundException extends Exception {
    public ProfesseurNotFoundException(Long id) {
        super("Professeur with id " + id + " not found");
    }
}
