package ma.enset.delibrations.entities.exceptions;

public class ProfesseurNotFoundException extends Exception {
    public ProfesseurNotFoundException(Long id) {
        super("Professeur with id " + id + " not found");
    }
    public ProfesseurNotFoundException(String id) {
        super("Professeur with id " + id + " not found");
    }
}
