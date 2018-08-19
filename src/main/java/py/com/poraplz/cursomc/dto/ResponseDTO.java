package py.com.poraplz.cursomc.dto;

/**
 * Respuesta de los servicios
 */
public class ResponseDTO {
    private DataDTO data;
    private ErrorsDTO errors;
    private MetaDTO meta;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public ErrorsDTO getErrors() {
        return errors;
    }

    public void setErrors(ErrorsDTO errors) {
        this.errors = errors;
    }

    public MetaDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDTO meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "data=" + data +
                ", errors=" + errors +
                ", meta=" + meta +
                '}';
    }
}
