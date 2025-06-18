package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderRequested extends AbstractEvent {

    private Long customerId;
    private RestaurantId restaurantId;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private Date orderTime;
    private String deliveryAddress;
    private PaymentMethod paymentMethod;

    public OrderRequested(Order aggregate) {
        super(aggregate);
    }

    public OrderRequested() {
        super();
    }
}
//>>> DDD / Domain Event
