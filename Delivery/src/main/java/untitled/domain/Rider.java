package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.DeliveryApplication;

@Entity
@Table(name = "Rider_table")
@Data
//<<< DDD / Aggregate Root
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long riderId;

    private RiderStatus status;

    private RiderProfile profile;

    public static RiderRepository repository() {
        RiderRepository riderRepository = DeliveryApplication.applicationContext.getBean(
            RiderRepository.class
        );
        return riderRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateRiderLocation(
        UpdateRiderLocationCommand updateRiderLocationCommand
    ) {
        //implement business logic here:

        RiderLocationUpdated riderLocationUpdated = new RiderLocationUpdated(
            this
        );
        riderLocationUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
