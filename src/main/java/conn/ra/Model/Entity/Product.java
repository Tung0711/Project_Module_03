package conn.ra.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(unique = true,length = 100)
    private String sku;

    @Column(name = "product_name",unique = true,length = 100)
    private String productName;

    private String description;

    @Column(name = "unit_price")
    private Double price;

    @Column(name = "stock_quantity")
    @Min ( 0 )
    private Integer stockQuantity;

    private String image;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date create;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "catalog_id",referencedColumnName = "id")
    private Categories categories;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<OrderDetails> orderDetails;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<WishList> wishLists;
}
