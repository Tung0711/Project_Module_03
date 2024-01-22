package conn.ra.Service.Categories;

import conn.ra.Model.Dto.Request.CategoriesRequest;
import conn.ra.Model.Entity.Categories;
import conn.ra.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Page<Categories> getAll(Pageable pageable) {
        return categoriesRepository.findAll ( pageable );
    }

    @Override
    public Categories findById(Long id) {
        return categoriesRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Categories add(CategoriesRequest categoriesRequest) {
        Categories categories = Categories.builder()
                .catalogName (categoriesRequest.getCatalogName ())
                .description(categoriesRequest.getDescription())
                .status(true)
                .build();
        return categoriesRepository.save ( categories );
    }

    @Override
    public Categories edit(CategoriesRequest categoriesRequest, Long id) {
        Categories categories = Categories.builder()
                .id(id)
                .catalogName (categoriesRequest.getCatalogName ())
                .description(categoriesRequest.getDescription())
                .status(true)
                .build();
        return categoriesRepository.save ( categories );
    }

    @Override
    public void delete(Long id) {
        categoriesRepository.deleteById ( id );
    }

    @Override
    public List<Categories> getByStatus() {
        return categoriesRepository.findByStatus(true);
    }
}
