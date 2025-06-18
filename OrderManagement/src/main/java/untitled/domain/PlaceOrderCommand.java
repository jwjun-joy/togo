package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceOrderCommand {

    private PaymentStatus paymentStatus;
    private Double totalAmount;
}
