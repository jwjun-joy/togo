package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RejectDeliveryCommand {

    private RiderId riderId;
    private String rejectReason;
}
