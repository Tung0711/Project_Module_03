package conn.ra.Service.Categories;

import conn.ra.Model.Dto.Response.CategoriesResponse;
import conn.ra.Model.Entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriesService {
    Page<CategoriesResponse> getAll(Pageable pageable);

    Categories findById(Long id);

    Categories save(Categories categories);

    void delete(Long id);
}
