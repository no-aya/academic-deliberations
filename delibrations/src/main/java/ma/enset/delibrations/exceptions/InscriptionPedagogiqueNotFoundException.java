package ma.enset.delibrations.exceptions;

public class InscriptionPedagogiqueNotFoundException extends Exception {
    public InscriptionPedagogiqueNotFoundException(Long id) {
        super("Inscription pedagogique with id " + id + " not found");
    }
}
