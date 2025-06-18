package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class OrderRequested extends AbstractEvent {

    private Long customerId;
    private Object restaurantId;
    private Object orderItems;
    private Object orderStatus;
    private Date orderTime;
    private String deliveryAddress;
    private Object paymentMethod;
}
