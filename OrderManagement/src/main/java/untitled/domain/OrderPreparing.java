package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPreparing extends AbstractEvent {

    private OrderStatus orderStatus;

    public OrderPreparing(Order aggregate) {
        super(aggregate);
    }

    public OrderPreparing() {
        super();
    }
}
//>>> DDD / Domain Event
