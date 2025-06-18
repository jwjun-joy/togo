package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PhoneNumberVerified extends AbstractEvent {

    private Long verificationId;
    private UserId userId;
    private String phoneNumber;
    private Boolean verified;
    private Date verifiedAt;

    public PhoneNumberVerified(PhoneVerification aggregate) {
        super(aggregate);
    }

    public PhoneNumberVerified() {
        super();
    }
}
//>>> DDD / Domain Event
