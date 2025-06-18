package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UserSuspended extends AbstractEvent {

    private UserStatus status;
    private String suspendedReason;

    public UserSuspended(User aggregate) {
        super(aggregate);
    }

    public UserSuspended() {
        super();
    }
}
//>>> DDD / Domain Event
