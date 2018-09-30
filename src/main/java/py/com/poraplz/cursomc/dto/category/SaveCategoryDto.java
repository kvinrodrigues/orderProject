package py.com.poraplz.cursomc.dto.category;

public class SaveCategoryDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "saveACategoryDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
