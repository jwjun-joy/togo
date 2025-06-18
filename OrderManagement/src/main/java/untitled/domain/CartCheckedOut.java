package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CartCheckedOut extends AbstractEvent {

    private Long customerId;
    private Double totalAmount;
    private String deliveryAddress;

    public CartCheckedOut(Cart aggregate) {
        super(aggregate);
    }

    public CartCheckedOut() {
        super();
    }
}
//>>> DDD / Domain Event
