package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuCreated extends AbstractEvent {

    private Long menuId;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private String allergyInfo;

    public MenuCreated(Restaurant aggregate) {
        super(aggregate);
    }

    public MenuCreated() {
        super();
    }
}
//>>> DDD / Domain Event
