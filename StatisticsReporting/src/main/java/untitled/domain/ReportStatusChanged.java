package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportStatusChanged extends AbstractEvent {

    private ReportStatus status;

    public ReportStatusChanged(Report aggregate) {
        super(aggregate);
    }

    public ReportStatusChanged() {
        super();
    }
}
//>>> DDD / Domain Event
