package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ReportListQuery {

    private ReportType reportType;
    private PeriodType periodType;
    private Date periodStart;
    private Date periodEnd;
    private ReportStatus status;
    private Integer page;
    private Integer pageSize;
}
