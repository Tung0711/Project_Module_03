package conn.ra.Controller.Admin;

import conn.ra.Model.Dto.Response.CategoriesResponse;
import conn.ra.Model.Entity.Categories;
import conn.ra.Service.Categories.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin/categories")
public class CategoryController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "5", name = "limit") int limit,
                                    @RequestParam(defaultValue = "0", name = "page") int page,
                                    @RequestParam(defaultValue = "catalogName", name = "sort") String sort
    ) {
        Pageable pageable = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        Page<CategoriesResponse> categories = categoriesService.getAll ( pageable );
        return new ResponseEntity<> ( categories, HttpStatus.CREATED );
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Categories categories) {
        Categories categoriesNew = categoriesService.save ( categories );
        return new ResponseEntity<> ( categoriesNew, HttpStatus.CREATED );
    }
}