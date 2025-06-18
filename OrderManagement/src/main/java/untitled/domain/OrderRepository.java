package untitled.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import untitled.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel="orders", path="orders")
public interface OrderRepository extends PagingAndSortingRepository<Order, LongLongRestaurantIdLongOrderStatusDateStringDoublePaymentMethodPaymentStatusStringList&lt;OrderItem&gt;>{
    @Query(value = "select order " +
        "from Order order " +
        "where(:customerId is null or order.customerId = :customerId) and (:orderStatus is null or order.orderStatus = :orderStatus) and (:orderTime is null or order.orderTime = :orderTime)")
       List<Order> orderStatusList
(Long customerId, OrderStatus orderStatus, Date orderTime, Pageable pageable);
    @Query(value = "select order " +
        "from Order order " +
        "where(:restaurantId is null or order.restaurantId = :restaurantId) and (:orderStatus is null or order.orderStatus = :orderStatus) and (:orderTime is null or order.orderTime = :orderTime)")
       List<Order> restaurantOrderStatusList
(RestaurantId restaurantId, OrderStatus orderStatus, Date orderTime, Pageable pageable);
}