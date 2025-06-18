package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CartCleared extends AbstractEvent {

    public CartCleared(Cart aggregate) {
        super(aggregate);
    }

    public CartCleared() {
        super();
    }
}
//>>> DDD / Domain Event
