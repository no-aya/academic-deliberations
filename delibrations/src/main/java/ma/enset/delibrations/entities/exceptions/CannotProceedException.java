package ma.enset.delibrations.entities.exceptions;

public class CannotProceedException extends Throwable {
    public CannotProceedException(String cannotUpdate) {
        super(cannotUpdate);
    }
}
