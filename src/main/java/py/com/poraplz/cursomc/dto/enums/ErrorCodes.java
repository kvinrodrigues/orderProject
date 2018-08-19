package py.com.poraplz.cursomc.dto.enums;

/**
 * Codigos de error a retornar en el response
 * Codigos de error a utilizar en las propiedades- "messages.properties"
 */
public enum ErrorCodes {
    INV_VALUE("0001");

    private String value;
    private ErrorCodes(String value){
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public ErrorCodes toEnum( Integer id ){
        if( id.equals(null))
            return null;

        for(ErrorCodes eCods:ErrorCodes.values()){
            if(id.equals(eCods)){
                return eCods;
            }
        }

        throw new IllegalArgumentException("Valor invalido");
    }
}
