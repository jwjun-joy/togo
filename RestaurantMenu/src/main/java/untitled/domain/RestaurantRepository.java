package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "restaurants",
    path = "restaurants"
)
public interface RestaurantRepository
    extends PagingAndSortingRepository<Restaurant, Long> {
    @Query(
        value = "select restaurant " +
        "from Restaurant restaurant " +
        "where(:latitude is null or restaurant.latitude = :latitude) and (:longitude is null or restaurant.longitude = :longitude) and (:sortBy is null or restaurant.sortBy like %:sortBy%) and (:page is null or restaurant.page = :page) and (:pageSize is null or restaurant.pageSize = :pageSize)"
    )
    List<Restaurant> nearbyRestaurantList(
        Double latitude,
        Double longitude,
        String sortBy,
        Integer page,
        Integer pageSize,
        Pageable pageable
    );

    @Query(
        value = "select restaurant " +
        "from Restaurant restaurant " +
        "where(:page is null or restaurant.page = :page) and (:pageSize is null or restaurant.pageSize = :pageSize)"
    )
    List<Restaurant> menuList(
        Integer page,
        Integer pageSize,
        Pageable pageable
    );
}
