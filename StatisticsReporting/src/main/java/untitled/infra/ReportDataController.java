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
// @RequestMapping(value="/reportData")
@Transactional
public class ReportDataController {

    @Autowired
    ReportDataRepository reportDataRepository;

    @RequestMapping(
        value = "/reportDatacreatereportdata",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public ReportData createReportData(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateReportDataCommand createReportDataCommand
    ) throws Exception {
        System.out.println("##### /reportData/createReportData  called #####");
        ReportData reportData = new ReportData();
        reportData.createReportData(createReportDataCommand);
        reportDataRepository.save(reportData);
        return reportData;
    }

    @RequestMapping(
        value = "/reportData/{id}/updatereportdata",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public ReportData updateReportData(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateReportDataCommand updateReportDataCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /reportData/updateReportData  called #####");
        Optional<ReportData> optionalReportData = reportDataRepository.findById(
            id
        );

        optionalReportData.orElseThrow(() -> new Exception("No Entity Found"));
        ReportData reportData = optionalReportData.get();
        reportData.updateReportData(updateReportDataCommand);

        reportDataRepository.save(reportData);
        return reportData;
    }
}
//>>> Clean Arch / Inbound Adaptor
