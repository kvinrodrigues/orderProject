package py.com.poraplz.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import py.com.poraplz.cursomc.entities.Categoria;
import py.com.poraplz.cursomc.entities.Producto;

import java.util.List;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
   @Transactional(readOnly = true)
   @Query("SELECT DISTINCT obj from Producto obj INNER  JOIN obj.categories cat where obj.name LIKE %:name% AND cat IN :categories")
   Page<Producto> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name, @Param("categories") List<Categoria> categories, Pageable pageRequest);


}
