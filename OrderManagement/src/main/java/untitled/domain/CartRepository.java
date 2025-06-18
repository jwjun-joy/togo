package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "carts", path = "carts")
public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {
    @Query(
        value = "select cart " +
        "from Cart cart " +
        "where(:customerId is null or cart.customerId = :customerId)"
    )
    List<Cart> cartList(Long customerId, Pageable pageable);
}
