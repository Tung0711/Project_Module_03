package conn.ra.Model.Dto.Request;

import conn.ra.Model.Entity.Categories;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoriesRequest {
    private String catalogName;
    private String description;
}
