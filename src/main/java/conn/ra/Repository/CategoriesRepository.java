package conn.ra.Repository;

import conn.ra.Model.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    List<Categories> findByStatus(boolean status);
}
