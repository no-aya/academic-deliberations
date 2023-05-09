package ma.enset.delibrations.exceptions;

public class SemestreNotFoundException extends Exception {
    public SemestreNotFoundException(Long id) {
        super("Semestre with id " + id + " not found");
    }

}
