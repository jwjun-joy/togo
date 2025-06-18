package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateReviewCommand {

    private Long customerId;
    private Integer rating;
    private String content;
}
