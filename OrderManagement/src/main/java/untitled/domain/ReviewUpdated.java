package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReviewUpdated extends AbstractEvent {

    private Integer rating;
    private String content;
    private Date updatedAt;

    public ReviewUpdated(Review aggregate) {
        super(aggregate);
    }

    public ReviewUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
