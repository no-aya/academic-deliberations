package ma.enset.delibrations.entities.exceptions;

public class EtudiantNotFoundException extends Exception {
    public EtudiantNotFoundException(String id) {
        super("Etudiant with code appoge " + id + " not found");
    }
    public EtudiantNotFoundException(Long id) {
        super("Etudiant with id " + id + " not found");
    }
}
