package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class StatisticsDataVisualizationQuery {

    private ReportDataType dataType;
    private Date periodStart;
    private Date periodEnd;
}
