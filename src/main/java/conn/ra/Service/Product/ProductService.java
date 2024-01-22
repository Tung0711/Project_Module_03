package conn.ra.Service.Product;

import conn.ra.Model.Dto.Request.ProductRequest;
import conn.ra.Model.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> getAll(Pageable pageable);

    Product findById(Long id);

    Product add(ProductRequest productRequest);
    Product edit(ProductRequest productRequest,Long id);

    void delete(Long id);
    List<Product> getByNameOrDes(String productName, String description);
}
