package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class RestaurantOrderStatusListQuery {

    private RestaurantId restaurantId;
    private OrderStatus orderStatus;
    private Date orderTime;
}
