package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPrepared extends AbstractEvent {

    private OrderStatus orderStatus;

    public OrderPrepared(Order aggregate) {
        super(aggregate);
    }

    public OrderPrepared() {
        super();
    }
}
//>>> DDD / Domain Event
