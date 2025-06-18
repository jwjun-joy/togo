package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class OrderStatusListQuery {

    private Long customerId;
    private OrderStatus orderStatus;
    private Date orderTime;
}
