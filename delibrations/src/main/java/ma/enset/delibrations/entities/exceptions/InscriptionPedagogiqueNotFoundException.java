package ma.enset.delibrations.entities.exceptions;

public class InscriptionPedagogiqueNotFoundException extends Exception {
    public InscriptionPedagogiqueNotFoundException(Long id) {
        super("Inscription pedagogique with id " + id + " not found");
    }
}
