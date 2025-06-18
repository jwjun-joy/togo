package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CartItemAdded extends AbstractEvent {

    private Long menuId;
    private Integer quantity;

    public CartItemAdded(Cart aggregate) {
        super(aggregate);
    }

    public CartItemAdded() {
        super();
    }
}
//>>> DDD / Domain Event
