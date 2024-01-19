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
            @RequestParam(defaultValue = "productName", name = "sort") String sort
    ) {
        Pageable pageable = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        Page<ProductResponse> products = productService.getAll ( pageable );
        return new ResponseEntity<> ( products, HttpStatus.CREATED );
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Product product) {
        Product productNew = productService.save ( product );
        return new ResponseEntity<> ( productNew, HttpStatus.CREATED );
    }
}
