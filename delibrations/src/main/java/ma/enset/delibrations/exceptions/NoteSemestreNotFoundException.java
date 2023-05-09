package ma.enset.delibrations.exceptions;

public class NoteSemestreNotFoundException extends Exception {
    public NoteSemestreNotFoundException(Long id) {
        super("NoteSemestre with id " + id + " not found");
    }
}
