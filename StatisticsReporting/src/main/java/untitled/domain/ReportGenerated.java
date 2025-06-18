package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReportGenerated extends AbstractEvent {

    private ReportType reportType;
    private PeriodType periodType;
    private ReportPeriod reportPeriod;
    private String generatedBy;
    private Date createdAt;
    private ReportStatus status;

    public ReportGenerated(Report aggregate) {
        super(aggregate);
    }

    public ReportGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
