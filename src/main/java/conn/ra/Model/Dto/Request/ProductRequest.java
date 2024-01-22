package conn.ra.Model.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String productName;
    private String description;
    private Double price;
    private Integer quantity;
    private String image;
    private Long catalogId;
}
