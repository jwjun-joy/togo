package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportDataCreated extends AbstractEvent {

    private ReportDataType dataType;
    private Double value;
    private Date recordDate;

    public ReportDataCreated(ReportData aggregate) {
        super(aggregate);
    }

    public ReportDataCreated() {
        super();
    }
}
//>>> DDD / Domain Event
