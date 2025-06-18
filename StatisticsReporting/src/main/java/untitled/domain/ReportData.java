package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.StatisticsReportingApplication;

@Entity
@Table(name = "ReportData_table")
@Data
//<<< DDD / Aggregate Root
public class ReportData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportDataId;

    private ReportDataType dataType;

    private Double value;

    private Date recordDate;

    private ReportId reportId;

    public static ReportDataRepository repository() {
        ReportDataRepository reportDataRepository = StatisticsReportingApplication.applicationContext.getBean(
            ReportDataRepository.class
        );
        return reportDataRepository;
    }

    //<<< Clean Arch / Port Method
    public void createReportData(
        CreateReportDataCommand createReportDataCommand
    ) {
        //implement business logic here:

        ReportDataCreated reportDataCreated = new ReportDataCreated(this);
        reportDataCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateReportData(
        UpdateReportDataCommand updateReportDataCommand
    ) {
        //implement business logic here:

        ReportDataUpdated reportDataUpdated = new ReportDataUpdated(this);
        reportDataUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
