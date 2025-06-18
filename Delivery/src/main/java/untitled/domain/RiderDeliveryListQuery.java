package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class RiderDeliveryListQuery {

    private RiderId riderId;
    private DeliveryStatus status;
}
