package conn.ra.Controller.Admin;

import conn.ra.Model.Dto.Request.ProductRequest;
import conn.ra.Model.Entity.Product;
import conn.ra.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<Product>> getAll(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "productName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "sortBy") String sortBy
    ) {
        Pageable pageable;
        if (sortBy.equals ( "asc" )) {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        } else {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).descending () );
        }
        Page<Product> products = productService.getAll ( pageable );
        return new ResponseEntity<> ( products, HttpStatus.OK );
    }

    @PostMapping("")
    public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest) {
        Product productCreate = productService.add ( productRequest );
        return new ResponseEntity<> ( productCreate, HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductRequest productRequest,@PathVariable("id") Long id) {
        Product productUpdate = productService.edit ( productRequest,id );
        return new ResponseEntity<> ( productUpdate, HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
        Product product = productService.findById ( id );
        if (product == null) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        } else {
            return new ResponseEntity<> ( product, HttpStatus.OK );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        productService.delete ( id );
        return new ResponseEntity<> ( "Đã xóa thành công", HttpStatus.OK );
    }
}
