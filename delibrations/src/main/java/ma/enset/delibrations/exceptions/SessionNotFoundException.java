package ma.enset.delibrations.exceptions;

public class SessionNotFoundException extends Exception {
    public SessionNotFoundException(String sessionNotFound) {
        super(sessionNotFound);
    }
}
