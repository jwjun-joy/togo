package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateRestaurantInfoCommand {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String openingTime;
    private String closingTime;
}
