package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RequestOrderCommand {

    private Long customerId;
    private RestaurantId restaurantId;
    private List<OrderItem> orderItems;
    private String deliveryAddress;
    private PaymentMethod paymentMethod;
}
