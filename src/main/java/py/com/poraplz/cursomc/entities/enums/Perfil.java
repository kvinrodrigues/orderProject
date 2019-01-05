package py.com.poraplz.cursomc.entities.enums;

public enum Perfil {

    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(2,"ROLE_CLIENT");

    private int cod;
    private String description;

    Perfil(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Perfil toEnum(Integer value){
        if(value==null)
            return null;
        for(Perfil perfil: Perfil.values()){
            if(value.equals(perfil.getCod()))
                return perfil;
        }
        throw new IllegalArgumentException("id invalido");
    }
}
