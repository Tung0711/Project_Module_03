package conn.ra.Service.Product;

import conn.ra.Model.Dto.Response.ProductResponse;
import conn.ra.Model.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponse> getAll(Pageable pageable);

    Product findById(Long id);

    Product save(Product product);

    void delete(Long id);
}
