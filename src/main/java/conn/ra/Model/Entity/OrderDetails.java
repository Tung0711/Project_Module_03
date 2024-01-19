package conn.ra.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OrderDetails {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(length = 100)
    private String name;

    @Column(name = "unit_price")
    private Double price;

    @Column(name = "order_quantity")
    @Min(1)
    private Integer orderQuantity;
}
