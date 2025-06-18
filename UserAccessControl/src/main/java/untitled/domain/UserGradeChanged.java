package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UserGradeChanged extends AbstractEvent {

    private String grade;

    public UserGradeChanged(User aggregate) {
        super(aggregate);
    }

    public UserGradeChanged() {
        super();
    }
}
//>>> DDD / Domain Event
