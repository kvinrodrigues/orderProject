package py.com.poraplz.cursomc.exceptions;

public class InvalidClaimsException extends RuntimeException{
    private static final long serialVersionUID=1L;

    public InvalidClaimsException(String message) {
        super(message);
    }

    public InvalidClaimsException(String message, Throwable cause) {
        super(message, cause);
    }
}
