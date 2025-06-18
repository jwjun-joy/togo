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
public class OrderItem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer quantity;
}
//>>> DDD / Value Object
