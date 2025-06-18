package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryLocationUpdated extends AbstractEvent {

    private RiderId riderId;
    private CurrentLocation currentLocation;
    private Date lastLocationUpdate;

    public DeliveryLocationUpdated(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryLocationUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
