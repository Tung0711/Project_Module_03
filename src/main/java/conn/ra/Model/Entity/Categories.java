package conn.ra.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String catalogName;
    private String description;
    @Column(columnDefinition = "boolean default true")
    private Boolean status;
    @OneToMany(mappedBy = "categories")
    @JsonIgnore
    List<Product> products;
}
