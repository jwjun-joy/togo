package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CheckoutCartCommand {

    private Long customerId;
    private String deliveryAddress;
}
