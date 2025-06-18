package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateReportCommand {

    private ReportType reportType;
    private PeriodType periodType;
    private ReportPeriod reportPeriod;
    private String generatedBy;
}
