package ma.enset.delibrations.entities.exceptions;

public class SessionNotFoundException extends Exception {
    public SessionNotFoundException(String sessionNotFound) {
        super(sessionNotFound);
    }
}
