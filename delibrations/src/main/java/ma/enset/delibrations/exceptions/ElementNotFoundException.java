package ma.enset.delibrations.exceptions;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(String elementCode) {
        super(elementCode);
    }
}
