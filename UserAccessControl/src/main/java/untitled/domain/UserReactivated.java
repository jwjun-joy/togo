package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UserReactivated extends AbstractEvent {

    private UserStatus status;

    public UserReactivated(User aggregate) {
        super(aggregate);
    }

    public UserReactivated() {
        super();
    }
}
//>>> DDD / Domain Event
