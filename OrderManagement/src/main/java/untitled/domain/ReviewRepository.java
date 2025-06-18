package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "reviews", path = "reviews")
public interface ReviewRepository
    extends PagingAndSortingRepository<Review, Long> {
    @Query(
        value = "select review " +
        "from Review review " +
        "where(:customerId is null or review.customerId = :customerId) and (:rating is null or review.rating = :rating)"
    )
    List<Review> reviewList(Long customerId, Integer rating, Pageable pageable);
}
