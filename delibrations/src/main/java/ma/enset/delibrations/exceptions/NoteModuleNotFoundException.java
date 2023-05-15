package ma.enset.delibrations.exceptions;

public class NoteModuleNotFoundException extends Exception{
    public NoteModuleNotFoundException(Long id) {
        super("Module note with id " + id + " not found");
    }

}
