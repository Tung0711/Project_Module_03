package conn.ra.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true, length = 100)
    @JsonFormat(pattern = "^[a-zA-Z0-9]+$")
    private String userName;

    private String email;

    @Column(length = 100, nullable = false)
    private String fullName;

    private Boolean status;

    @Column(unique = true)
    private String password;

    @Column(unique = true, length = 15)
    @JsonFormat(pattern = "^0[1-9]\\d{8}$")
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<> ();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Orders> orders;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Address> addresses;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<WishList> wishLists;
}
