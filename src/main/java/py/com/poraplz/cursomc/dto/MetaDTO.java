package py.com.poraplz.cursomc.dto;

/**
 * Datos especificos: numero de pagina, total de paginas, cantidad de elementos.
 */
public class MetaDTO {
    private Integer pageSize;
    private Integer totalPages;
    private Long totalCount;

    public MetaDTO() {
    }

    public MetaDTO(Integer pageSize, Integer totalPages, Long totalCount) {
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }


}
