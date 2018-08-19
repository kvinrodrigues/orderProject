package py.com.poraplz.cursomc.entities.enums;

public enum EstadoPagamento {
    PENDIENTE(1,""),
    QUITADO(2," "),
    CANCELADO(3," ");

    private int value;
    private String description;

    private EstadoPagamento(Integer value, String desc){
        this.value = value;
        this.description = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static EstadoPagamento toEnum(Integer id){
        if(id.equals(null))
            return null;

        for(EstadoPagamento e: EstadoPagamento.values()){
            if(id.equals(e)){
                return e;
            }
        }
        throw new IllegalArgumentException("id invalido");
    }

}
