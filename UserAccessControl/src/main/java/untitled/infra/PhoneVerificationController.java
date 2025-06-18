package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/phoneVerifications")
@Transactional
public class PhoneVerificationController {

    @Autowired
    PhoneVerificationRepository phoneVerificationRepository;

    @RequestMapping(
        value = "/phoneVerificationscreatephoneverification",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public PhoneVerification createPhoneVerification(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreatePhoneVerificationCommand createPhoneVerificationCommand
    ) throws Exception {
        System.out.println(
            "##### /phoneVerification/createPhoneVerification  called #####"
        );
        PhoneVerification phoneVerification = new PhoneVerification();
        phoneVerification.createPhoneVerification(
            createPhoneVerificationCommand
        );
        phoneVerificationRepository.save(phoneVerification);
        return phoneVerification;
    }

    @RequestMapping(
        value = "/phoneVerifications/{id}/verifyphonenumber",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public PhoneVerification verifyPhoneNumber(
        @PathVariable(value = "id") Long id,
        @RequestBody VerifyPhoneNumberCommand verifyPhoneNumberCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /phoneVerification/verifyPhoneNumber  called #####"
        );
        Optional<PhoneVerification> optionalPhoneVerification = phoneVerificationRepository.findById(
            id
        );

        optionalPhoneVerification.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        PhoneVerification phoneVerification = optionalPhoneVerification.get();
        phoneVerification.verifyPhoneNumber(verifyPhoneNumberCommand);

        phoneVerificationRepository.save(phoneVerification);
        return phoneVerification;
    }
}
//>>> Clean Arch / Inbound Adaptor
