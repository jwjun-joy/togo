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
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;

    private String phoneNumber;

    private UserType userType;

    private UserStatus status;

    private String grade;

    private Date registeredAt;

    private String suspendedReason;

    public static UserRepository repository() {
        UserRepository userRepository = UserAccessControlApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void suspendUser(SuspendUserCommand suspendUserCommand) {
        //implement business logic here:

        UserSuspended userSuspended = new UserSuspended(this);
        userSuspended.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void reactivateUser() {
        //implement business logic here:

        UserReactivated userReactivated = new UserReactivated(this);
        userReactivated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void changeUserGrade(ChangeUserGradeCommand changeUserGradeCommand) {
        //implement business logic here:

        UserGradeChanged userGradeChanged = new UserGradeChanged(this);
        userGradeChanged.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
