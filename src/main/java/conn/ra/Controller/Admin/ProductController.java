package conn.ra.Controller.Admin;

import conn.ra.Model.Dto.Response.ProductResponse;
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
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "productName", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "sort_by") String sort_by
    ) {
        Pageable pageable;
        if (sort_by.equals ( "asc" )) {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        } else {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).descending () );
        }
        Page<ProductResponse> products = productService.getAll ( pageable );
        return new ResponseEntity<> ( products, HttpStatus.OK );
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Product product) {
        Product productCreate = productService.save ( product );
        return new ResponseEntity<> ( productCreate, HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product) {
        Product productUpdate = productService.save ( product );
        return new ResponseEntity<> ( productUpdate, HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Product product = productService.findById ( id );
        return new ResponseEntity<> ( product, HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete ( id );
        return new ResponseEntity<> ( "Đã xóa thành công", HttpStatus.OK );
    }
}
