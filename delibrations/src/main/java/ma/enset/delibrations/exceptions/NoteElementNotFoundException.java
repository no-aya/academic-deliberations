package ma.enset.delibrations.exceptions;

public class NoteElementNotFoundException extends Exception {
    public NoteElementNotFoundException(String s) {
        super("Note Element with id " + s + " not found");
    }
}
