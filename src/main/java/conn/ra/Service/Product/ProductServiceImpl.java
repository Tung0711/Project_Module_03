package conn.ra.Service.Product;

import conn.ra.Model.Dto.Response.ProductResponse;
import conn.ra.Model.Entity.Product;
import conn.ra.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll ( pageable );
        return products.map ( ProductResponse::new );
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Product save(Product product) {
        return productRepository.save ( product );
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById ( id );
    }
}
