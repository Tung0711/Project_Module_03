package conn.ra.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String productName;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String image;
    @ManyToOne
    @JoinColumn(name = "catalog_id",referencedColumnName = "id")
    private Categories categories;
}
