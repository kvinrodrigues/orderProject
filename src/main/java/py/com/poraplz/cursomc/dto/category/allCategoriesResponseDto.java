package py.com.poraplz.cursomc.dto.category;

public class allCategoriesResponseDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "allCategoriesResponseDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
