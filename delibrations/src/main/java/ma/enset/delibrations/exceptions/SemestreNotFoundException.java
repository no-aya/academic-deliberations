package ma.enset.delibrations.exceptions;

public class SemestreNotFoundException extends Exception {
    public SemestreNotFoundException(String id) {
        super("Semestre with code " + id + " not found");
    }
    public SemestreNotFoundException(Long id) {
        super("Semestre with id " + id + " not found");
    }

}
