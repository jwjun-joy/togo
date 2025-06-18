package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CartItemRemoved extends AbstractEvent {

    private Long menuId;

    public CartItemRemoved(Cart aggregate) {
        super(aggregate);
    }

    public CartItemRemoved() {
        super();
    }
}
//>>> DDD / Domain Event
