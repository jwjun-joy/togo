package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum OrderStatus {
    REQUESTED,
    PLACED,
    ACCEPTED,
    PREPARING,
    PREPARED,
    DELIVERY_REQUESTED,
    DELIVERING,
    COMPLETED,
    CANCELLED,
}
