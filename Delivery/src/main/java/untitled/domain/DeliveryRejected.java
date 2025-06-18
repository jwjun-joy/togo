package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryRejected extends AbstractEvent {

    private RiderId riderId;
    private String rejectReason;
    private DeliveryStatus status;

    public DeliveryRejected(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryRejected() {
        super();
    }
}
//>>> DDD / Domain Event
