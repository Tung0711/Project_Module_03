package conn.ra.Repository;

import conn.ra.Model.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a from Address a where a.user.id = :id")
    List<Address> findAllByUserId(Long id);
    @Modifying
    @Query("delete from Address a where a.id = :addressId and a.user.id = :userId")
    void deleteByIdAndUserId(Long addressId, Long userId);

    @Query("SELECT a from Address a where a.id = :addressId and a.user.id = :userId")
    Address findByIdAndUserId(Long addressId, Long userId);
}
