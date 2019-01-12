package py.com.poraplz.cursomc.exceptions;

import java.io.Serializable;

public class AuthorizationException extends RuntimeException implements Serializable{
    private static final long serialVersionUID = 1L;

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
