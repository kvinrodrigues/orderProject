package py.com.poraplz.cursomc.entities.enums;


public enum TipoCliente {
    PERSONA_FISICA(1,"Persona Fisica"),
    PERSONA_JURIDICA(2, "Persona Juridica");

    private int cod;
    private String descripcion;

    private TipoCliente(int cod, String descripcion){
        this.cod = cod;
        this.descripcion = descripcion;
    }

    public static TipoCliente toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (TipoCliente tipoCliente:TipoCliente.values()){
            if (cod.equals(tipoCliente.getCod())){
                return tipoCliente;
            }
        }
        throw new IllegalArgumentException("id invalido: "+ cod);
    }


    public int getCod() {
        return cod;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
