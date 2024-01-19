package conn.ra.Model.Dto.Response;

import conn.ra.Model.Entity.Categories;
import conn.ra.Model.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private Double price;
    private String image;
    private Categories categories;

    public ProductResponse(Product product) {
        this.id = product.getId ();
        this.productName = product.getProductName ();
        this.price = product.getPrice ();
        this.image = product.getImage ();
        this.categories = product.getCategories ();
    }
}
