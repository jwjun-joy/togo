package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/reports")
@Transactional
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @RequestMapping(
        value = "/reportscreatereport",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Report createReport(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateReportCommand createReportCommand
    ) throws Exception {
        System.out.println("##### /report/createReport  called #####");
        Report report = new Report();
        report.createReport(createReportCommand);
        reportRepository.save(report);
        return report;
    }

    @RequestMapping(
        value = "/reports/{id}/changereportstatus",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Report changeReportStatus(
        @PathVariable(value = "id") ReportStatus id,
        @RequestBody ChangeReportStatusCommand changeReportStatusCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /report/changeReportStatus  called #####");
        Optional<Report> optionalReport = reportRepository.findById(id);

        optionalReport.orElseThrow(() -> new Exception("No Entity Found"));
        Report report = optionalReport.get();
        report.changeReportStatus(changeReportStatusCommand);

        reportRepository.save(report);
        return report;
    }
}
//>>> Clean Arch / Inbound Adaptor
