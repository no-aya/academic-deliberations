package ma.enset.delibrations.exceptions;

public class CannotProceedException extends Throwable {
    public CannotProceedException(String cannotUpdate) {
        super(cannotUpdate);
    }
}
