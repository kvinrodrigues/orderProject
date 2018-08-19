package py.com.poraplz.cursomc.dto;

import py.com.poraplz.cursomc.dto.enums.ErrorCodes;
import py.com.poraplz.cursomc.utils.Messages;

public class ErrorsDTO {
    private ErrorCodes errorCodes;
    private String errorCode;
    private Messages message;

    public ErrorsDTO(ErrorCodes errorCodes, String errorCode, Messages message) {
        this.errorCodes = errorCodes;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }
}
