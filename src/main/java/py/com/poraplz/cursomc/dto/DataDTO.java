package py.com.poraplz.cursomc.dto;

import py.com.poraplz.cursomc.dto.enums.DataCodes;
import py.com.poraplz.cursomc.utils.Messages;

import java.util.Map;

public class DataDTO {
    private DataCodes dataCodes;
    private String dataCode;
    private Messages message;
    private Map<String,Object> body;

    public DataCodes getDataCodes() {
        return dataCodes;
    }

    public void setDataCodes(DataCodes dataCodes) {
        this.dataCodes = dataCodes;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
