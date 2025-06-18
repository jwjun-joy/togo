package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryAccepted extends AbstractEvent {

    private RiderId riderId;
    private DeliveryStatus status;

    public DeliveryAccepted(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryAccepted() {
        super();
    }
}
//>>> DDD / Domain Event
