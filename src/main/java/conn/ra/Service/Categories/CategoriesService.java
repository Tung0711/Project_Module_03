package conn.ra.Service.Categories;

import conn.ra.Model.Dto.Request.CategoriesRequest;
import conn.ra.Model.Entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriesService {
    Page<Categories> getAll(Pageable pageable);

    Categories findById(Long id);

    Categories add(CategoriesRequest categoriesRequest);
    Categories edit(CategoriesRequest categoriesRequest,Long id);

    void delete(Long id);
    List<Categories> getByStatus();
}
