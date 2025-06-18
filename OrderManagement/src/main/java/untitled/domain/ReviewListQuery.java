package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ReviewListQuery {

    private Long customerId;
    private Integer rating;
}
