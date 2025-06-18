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
@Table(name = "Report_table")
@Data
//<<< DDD / Aggregate Root
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ReportType reportType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PeriodType periodType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ReportPeriod reportPeriod;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String generatedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ReportStatus status;

    public static ReportRepository repository() {
        ReportRepository reportRepository = StatisticsReportingApplication.applicationContext.getBean(
            ReportRepository.class
        );
        return reportRepository;
    }

    //<<< Clean Arch / Port Method
    public void createReport(CreateReportCommand createReportCommand) {
        //implement business logic here:

        ReportGenerated reportGenerated = new ReportGenerated(this);
        reportGenerated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void changeReportStatus(
        ChangeReportStatusCommand changeReportStatusCommand
    ) {
        //implement business logic here:

        ReportStatusChanged reportStatusChanged = new ReportStatusChanged(this);
        reportStatusChanged.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
