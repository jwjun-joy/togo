package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportDataUpdated extends AbstractEvent {

    private Double value;

    public ReportDataUpdated(ReportData aggregate) {
        super(aggregate);
    }

    public ReportDataUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
