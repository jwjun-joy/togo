package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class PhoneVerificationListQuery {

    private UserId userId;
    private String phoneNumber;
    private Boolean verified;
    private Date createdAt;
}
