package ma.enset.delibrations.exceptions;

public class ModuleNotFoundException extends Exception {
    public ModuleNotFoundException(Long id) {
        super("Module with id " + id + " not found");
    }
}
