package py.com.poraplz.cursomc.dto.enums;

/**
 * Codigos a retornar en el response
 * Codigos a utilizar en las propiedades- "messages.properties"
 */
public enum DataCodes {

    OK("0000");

    private String value;
    private DataCodes(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataCodes toEnum(Integer id){
        if(id.equals(null))
            return null;

        for(DataCodes eCods: DataCodes.values()){
            if(id.equals(eCods)){
                return eCods;
            }
        }
        throw new IllegalArgumentException("Valor invalido");
    }
}
