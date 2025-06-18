package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "reports", path = "reports")
public interface ReportRepository
    extends
        PagingAndSortingRepository<Report, LongReportTypePeriodTypeReportPeriodStringDateReportStatus> {
    @Query(
        value = "select report " +
        "from Report report " +
        "where(:reportType is null or report.reportType = :reportType) and (:periodType is null or report.periodType = :periodType) and (:periodStart is null or report.periodStart = :periodStart) and (:periodEnd is null or report.periodEnd = :periodEnd) and (:status is null or report.status = :status) and (:page is null or report.page = :page) and (:pageSize is null or report.pageSize = :pageSize)"
    )
    List<Report> reportList(
        ReportType reportType,
        PeriodType periodType,
        Date periodStart,
        Date periodEnd,
        ReportStatus status,
        Integer page,
        Integer pageSize,
        Pageable pageable
    );

    @Query(
        value = "select report " +
        "from Report report " +
        "where(:format is null or report.format like %:format%)"
    )
    Report reportDownload(String format);
}
