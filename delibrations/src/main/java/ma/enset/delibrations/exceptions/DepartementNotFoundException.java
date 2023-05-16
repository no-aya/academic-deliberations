package ma.enset.delibrations.exceptions;

public class DepartementNotFoundException extends Exception{
    public DepartementNotFoundException(Long departementId){ super("departement de id "+departementId +"not found");}
    public DepartementNotFoundException(String departementCode){ super("departement de"+departementCode+"not found");}
}
