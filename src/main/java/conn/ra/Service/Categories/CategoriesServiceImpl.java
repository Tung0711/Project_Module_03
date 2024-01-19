package conn.ra.Service.Categories;

import conn.ra.Model.Dto.Response.CategoriesResponse;
import conn.ra.Model.Entity.Categories;
import conn.ra.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Page<CategoriesResponse> getAll(Pageable pageable) {
        Page<Categories> categories = categoriesRepository.findAll ( pageable );
        return categories.map ( CategoriesResponse::new );
    }

    @Override
    public Categories findById(Long id) {
        return categoriesRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Categories save(Categories categories) {
        return categoriesRepository.save ( categories );
    }

    @Override
    public void delete(Long id) {
        categoriesRepository.deleteById ( id );
    }
}
