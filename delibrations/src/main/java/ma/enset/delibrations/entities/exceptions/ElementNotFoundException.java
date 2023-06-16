package ma.enset.delibrations.entities.exceptions;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(String elementCode) {
        super(elementCode);
    }
}
