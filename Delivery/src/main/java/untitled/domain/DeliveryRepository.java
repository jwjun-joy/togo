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
    collectionResourceRel = "deliveries",
    path = "deliveries"
)
public interface DeliveryRepository
    extends
        PagingAndSortingRepository<Delivery, LongRiderIdDeliveryStatusCurrentLocationDate> {
    @Query(
        value = "select delivery " +
        "from Delivery delivery " +
        "where(:riderId is null or delivery.riderId = :riderId) and (:status is null or delivery.status = :status)"
    )
    List<Delivery> riderDeliveryList(
        RiderId riderId,
        DeliveryStatus status,
        Pageable pageable
    );
}
