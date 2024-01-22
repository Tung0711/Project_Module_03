package conn.ra.Controller.Auth;

import conn.ra.Model.Entity.Categories;
import conn.ra.Model.Entity.Product;
import conn.ra.Service.Categories.CategoriesService;
import conn.ra.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PermitAllController {
    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> findCategories() {
        List<Categories> categories = categoriesService.getByStatus ();
        return new ResponseEntity<> ( categories, HttpStatus.OK );
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String query) {
        List<Product> products = productService.getByNameOrDes ( query, query );
        return new ResponseEntity<> ( products, HttpStatus.OK );
    }
}