package py.com.poraplz.cursomc.utils;

public class Messages {
    private String message;
    private String detail;

    public Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    @Override
    public String toString() {
        return "Messages{" +
                "message='" + message + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
