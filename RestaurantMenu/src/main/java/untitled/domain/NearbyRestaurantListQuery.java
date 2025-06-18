package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class NearbyRestaurantListQuery {

    private Double latitude;
    private Double longitude;
    private String sortBy;
    private Integer page;
    private Integer pageSize;
}
