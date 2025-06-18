package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PhoneVerificationCreated extends AbstractEvent {

    private Long verificationId;
    private UserId userId;
    private String phoneNumber;
    private Boolean verified;
    private Date createdAt;

    public PhoneVerificationCreated(PhoneVerification aggregate) {
        super(aggregate);
    }

    public PhoneVerificationCreated() {
        super();
    }
}
//>>> DDD / Domain Event
