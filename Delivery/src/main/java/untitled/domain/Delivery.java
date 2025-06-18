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
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private RiderId riderId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private DeliveryStatus status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private CurrentLocation currentLocation;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date lastLocationUpdate;

    private OrderId orderId;

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public void requestDelivery(RequestDeliveryCommand requestDeliveryCommand) {
        //implement business logic here:

        DeliveryRequested deliveryRequested = new DeliveryRequested(this);
        deliveryRequested.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void acceptDelivery(AcceptDeliveryCommand acceptDeliveryCommand) {
        //implement business logic here:

        DeliveryAccepted deliveryAccepted = new DeliveryAccepted(this);
        deliveryAccepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void rejectDelivery(RejectDeliveryCommand rejectDeliveryCommand) {
        //implement business logic here:

        DeliveryRejected deliveryRejected = new DeliveryRejected(this);
        deliveryRejected.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void startDelivery() {
        //implement business logic here:

        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateDeliveryLocation(
        UpdateDeliveryLocationCommand updateDeliveryLocationCommand
    ) {
        //implement business logic here:

        DeliveryLocationUpdated deliveryLocationUpdated = new DeliveryLocationUpdated(
            this
        );
        deliveryLocationUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void completeDelivery() {
        //implement business logic here:

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
