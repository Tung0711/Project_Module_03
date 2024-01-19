package conn.ra.Model.Dto.Response;

import conn.ra.Model.Entity.Categories;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoriesResponse {
    private Long id;
    private String catalogName;
    private String description;
    private Boolean status;

    public CategoriesResponse(Categories categories) {
        this.id = categories.getId ();
        this.catalogName = categories.getCatalogName ();
        this.description = categories.getDescription ();
        this.status = categories.getStatus ();
    }
}
