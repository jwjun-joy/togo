package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.OrderManagementApplication;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private RestaurantId restaurantId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryPersonId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private OrderStatus orderStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date orderTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String deliveryAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Double totalAmount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PaymentMethod paymentMethod;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PaymentStatus paymentStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cancelReason;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ElementCollection
    private List<OrderItem> orderItems;

    private UserId userId;

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderManagementApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    //<<< Clean Arch / Port Method
    public void requestOrder(RequestOrderCommand requestOrderCommand) {
        //implement business logic here:

        OrderRequested orderRequested = new OrderRequested(this);
        orderRequested.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void placeOrder(PlaceOrderCommand placeOrderCommand) {
        //implement business logic here:

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cancelOrder(CancelOrderCommand cancelOrderCommand) {
        //implement business logic here:

        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void acceptOrder() {
        //implement business logic here:

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void rejectOrder(RejectOrderCommand rejectOrderCommand) {
        //implement business logic here:

        OrderRejectedByRestaurant orderRejectedByRestaurant = new OrderRejectedByRestaurant(
            this
        );
        orderRejectedByRestaurant.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void prepareOrder() {
        //implement business logic here:

        OrderPreparing orderPreparing = new OrderPreparing(this);
        orderPreparing.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void completePreparation() {
        //implement business logic here:

        OrderPrepared orderPrepared = new OrderPrepared(this);
        orderPrepared.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
