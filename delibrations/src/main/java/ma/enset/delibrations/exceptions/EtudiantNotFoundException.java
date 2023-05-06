package ma.enset.delibrations.exceptions;

public class EtudiantNotFoundException extends Exception {
    public EtudiantNotFoundException(String id) {
        super("Etudiant with id " + id + " not found");
    }
}
