package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderRejectedByRestaurant extends AbstractEvent {

    private RestaurantId restaurantId;
    private String cancelReason;
    private OrderStatus orderStatus;

    public OrderRejectedByRestaurant(Order aggregate) {
        super(aggregate);
    }

    public OrderRejectedByRestaurant() {
        super();
    }
}
//>>> DDD / Domain Event
