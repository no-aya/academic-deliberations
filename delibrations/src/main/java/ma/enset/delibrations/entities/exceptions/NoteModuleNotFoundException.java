package ma.enset.delibrations.entities.exceptions;

public class NoteModuleNotFoundException extends Exception{
    public NoteModuleNotFoundException(Long id) {
        super("Note Module with id " + id + " not found");
    }

}
