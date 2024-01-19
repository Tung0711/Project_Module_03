package conn.ra.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    private String name;
    private Double price;
    private Integer orderQuantity;
}
