package conn.ra.Service.Product;

import conn.ra.Model.Dto.Request.ProductRequest;
import conn.ra.Model.Entity.Categories;
import conn.ra.Model.Entity.Product;
import conn.ra.Repository.ProductRepository;
import conn.ra.Service.Categories.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoriesService categoriesService;

    @Override
    public Page<Product> getAll(Pageable pageable) {
         return productRepository.findAll ( pageable );
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Product add(ProductRequest productRequest) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (productRepository.existsByProductName(productRequest.getProductName ())){
            throw new RuntimeException("tên sản phẩm đã tồn tại!");
        }
        Categories categories = categoriesService.findById(productRequest.getCatalogId ());

        if (categories == null) {
            throw new RuntimeException("Không tồn tại danh mục!");
        }

        Product product = Product.builder()
                .sku( UUID.randomUUID().toString())
                .productName (productRequest.getProductName ())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stockQuantity (productRequest.getQuantity())
                .image(productRequest.getImage())
                .categories (categories)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product edit(ProductRequest productRequest,Long id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (productRepository.existsByProductName(productRequest.getProductName ())){
            throw new RuntimeException("Tên sản phẩm đã tồn tại!");
        }

        Categories categories = categoriesService.findById(productRequest.getCatalogId ());

        if (categories == null) {
            throw new RuntimeException("Không tồn tại danh mục!");
        }

        Product product = Product.builder()
                .id(id)
                .productName (productRequest.getProductName ())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stockQuantity (productRequest.getQuantity())
                .image(productRequest.getImage())
                .categories (categories)
                .build();
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById ( id );
    }

    @Override
    public List<Product> getByNameOrDes(String productName, String description) {
        return productRepository.findByProductNameOrDescription(productName, description);
    }
}
