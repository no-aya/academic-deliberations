package ma.enset.delibrations.exceptions;

public class DepartementNotFoundException extends Exception{
    public DepartementNotFoundException(Long departementId){ super("id"+departementId);}
}
