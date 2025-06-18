package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateReportDataCommand {

    private ReportDataType dataType;
    private Double value;
    private Date recordDate;
}
