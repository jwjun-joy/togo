package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class RestaurantInfoChanged extends AbstractEvent {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String openingTime;
    private String closingTime;

    public RestaurantInfoChanged(Restaurant aggregate) {
        super(aggregate);
    }

    public RestaurantInfoChanged() {
        super();
    }
}
//>>> DDD / Domain Event
