package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "phoneVerifications",
    path = "phoneVerifications"
)
public interface PhoneVerificationRepository
    extends PagingAndSortingRepository<PhoneVerification, Long> {
    @Query(
        value = "select phoneVerification " +
        "from PhoneVerification phoneVerification " +
        "where(:userId is null or phoneVerification.userId = :userId) and (:phoneNumber is null or phoneVerification.phoneNumber like %:phoneNumber%) and (phoneVerification.verified = :verified) and (:createdAt is null or phoneVerification.createdAt = :createdAt)"
    )
    List<PhoneVerification> phoneVerificationList(
        UserId userId,
        String phoneNumber,
        Boolean verified,
        Date createdAt,
        Pageable pageable
    );

    @Query(
        value = "select phoneVerification " +
        "from PhoneVerification phoneVerification " +
        "where(:verificationId is null or phoneVerification.verificationId = :verificationId)"
    )
    PhoneVerification phoneVerificationDetails(Long verificationId);
}
