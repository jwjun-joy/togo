package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreatePhoneVerificationCommand {

    private UserId userId;
    private String phoneNumber;
}
