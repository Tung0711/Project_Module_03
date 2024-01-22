package conn.ra.Controller.Admin;

import conn.ra.Model.Dto.Request.CategoriesRequest;
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
    public ResponseEntity<Page<Categories>> getAll(@RequestParam(defaultValue = "5", name = "limit") int limit,
                                    @RequestParam(defaultValue = "0", name = "page") int page,
                                    @RequestParam(defaultValue = "catalogName", name = "sort") String sort,
                                    @RequestParam(defaultValue = "asc", name = "sortBy") String sortBy
    ) {
        Pageable pageable;
        if (sortBy.equals ( "asc" )) {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        } else {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).descending () );
        }
        Page<Categories> categories = categoriesService.getAll ( pageable );
        return new ResponseEntity<> ( categories, HttpStatus.OK );
    }

    @PostMapping("")
    public ResponseEntity<Categories> create(@RequestBody CategoriesRequest categoriesRequest) {
        Categories categoriesCreate = categoriesService.add ( categoriesRequest );
        return new ResponseEntity<> ( categoriesCreate, HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> update(@RequestBody CategoriesRequest categoriesRequest,@PathVariable("id") Long id) {
        Categories categoriesUpdate = categoriesService.edit ( categoriesRequest,id );
        return new ResponseEntity<> ( categoriesUpdate, HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getById(@PathVariable("id") Long id) {
        Categories categories = categoriesService.findById ( id );
        if(categories == null) {
            throw new RuntimeException("Danh mục không tồn tại!");
        }else {
            return new ResponseEntity<> ( categories, HttpStatus.OK );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        categoriesService.delete ( id );
        return new ResponseEntity<> ( "Đã xóa thành công", HttpStatus.OK );
    }
}
