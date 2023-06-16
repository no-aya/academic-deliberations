package ma.enset.delibrations.entities.exceptions;

public class ModuleNotFoundException extends Exception {
    public ModuleNotFoundException(String id) {
        super("Module with code " + id + " not found");
    }
    public ModuleNotFoundException(Long id) {
        super("Module with id " + id + " not found");
    }
}
