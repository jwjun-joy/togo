package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.UserAccessControlApplication;

@Entity
@Table(name = "PhoneVerification_table")
@Data
//<<< DDD / Aggregate Root
public class PhoneVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long verificationId;

    private String phoneNumber;

    private Boolean verified;

    private Date verifiedAt;

    private UserId userId;

    public static PhoneVerificationRepository repository() {
        PhoneVerificationRepository phoneVerificationRepository = UserAccessControlApplication.applicationContext.getBean(
            PhoneVerificationRepository.class
        );
        return phoneVerificationRepository;
    }

    //<<< Clean Arch / Port Method
    public void createPhoneVerification(
        CreatePhoneVerificationCommand createPhoneVerificationCommand
    ) {
        //implement business logic here:

        PhoneVerificationCreated phoneVerificationCreated = new PhoneVerificationCreated(
            this
        );
        phoneVerificationCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void verifyPhoneNumber(
        VerifyPhoneNumberCommand verifyPhoneNumberCommand
    ) {
        //implement business logic here:

        PhoneNumberVerified phoneNumberVerified = new PhoneNumberVerified(this);
        phoneNumberVerified.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
