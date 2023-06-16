package ma.enset.delibrations.entities.exceptions;

public class FiliereNotFoundException extends Exception{
    public FiliereNotFoundException(String filiereId){ super("id "+filiereId);}
}
