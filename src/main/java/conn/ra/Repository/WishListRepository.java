package conn.ra.Repository;

import conn.ra.Model.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository  extends JpaRepository<WishList,Long> {
    @Query("select w from WishList w where w.user.id = :userId")
    List<WishList> getAllByUserId(Long userId);
    @Modifying
    @Query("delete from WishList w where w.id = :wishListId and w.user.id = :userId")
    void deleteByIdAndUserId(Long wishListId, Long userId);
}
