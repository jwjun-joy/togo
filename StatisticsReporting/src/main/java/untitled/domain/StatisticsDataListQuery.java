package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class StatisticsDataListQuery {

    private ReportDataType dataType;
    private Date recordDate;
    private Integer page;
    private Integer pageSize;
}
