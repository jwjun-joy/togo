package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "reportData",
    path = "reportData"
)
public interface ReportDataRepository
    extends PagingAndSortingRepository<ReportData, Long> {
    @Query(
        value = "select reportData " +
        "from ReportData reportData " +
        "where(:dataType is null or reportData.dataType = :dataType) and (:recordDate is null or reportData.recordDate = :recordDate) and (:page is null or reportData.page = :page) and (:pageSize is null or reportData.pageSize = :pageSize)"
    )
    List<ReportData> statisticsDataList(
        ReportDataType dataType,
        Date recordDate,
        Integer page,
        Integer pageSize,
        Pageable pageable
    );

    @Query(
        value = "select reportData " +
        "from ReportData reportData " +
        "where(:dataType is null or reportData.dataType = :dataType) and (:periodStart is null or reportData.periodStart = :periodStart) and (:periodEnd is null or reportData.periodEnd = :periodEnd)"
    )
    List<ReportData> statisticsDataVisualization(
        ReportDataType dataType,
        Date periodStart,
        Date periodEnd,
        Pageable pageable
    );

    @Query(
        value = "select reportData " +
        "from ReportData reportData " +
        "where(:format is null or reportData.format like %:format%)"
    )
    List<ReportData> statisticsDataDownload(String format, Pageable pageable);
}
