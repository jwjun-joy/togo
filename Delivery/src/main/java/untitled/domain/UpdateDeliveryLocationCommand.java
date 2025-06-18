package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateDeliveryLocationCommand {

    private CurrentLocation currentLocation;
    private Date lastLocationUpdate;
}
