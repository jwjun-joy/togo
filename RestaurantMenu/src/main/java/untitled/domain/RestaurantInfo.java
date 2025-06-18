package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfo {

    private String name;

    private String address;

    private Double latitude;

    private Double longitude;

    private String openingTime;

    private String closingTime;

    private Double rating;

    private Integer popularity;
}
//>>> DDD / Value Object
