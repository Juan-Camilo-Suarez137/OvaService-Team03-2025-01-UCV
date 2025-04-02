package co.edu.uceva.ovaservice.domain.exception;

public class OvaNoEncontradoException extends RuntimeException {
    public OvaNoEncontradoException(long id) {super("El ova con id " + id + " no fue encontrado");}
}
