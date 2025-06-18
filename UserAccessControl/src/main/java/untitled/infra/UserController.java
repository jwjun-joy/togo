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
// @RequestMapping(value="/users")
@Transactional
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(
        value = "/users/{id}/suspenduser",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User suspendUser(
        @PathVariable(value = "id") Long id,
        @RequestBody SuspendUserCommand suspendUserCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/suspendUser  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.suspendUser(suspendUserCommand);

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/reactivateuser",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User reactivateUser(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/reactivateUser  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.reactivateUser();

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/changeusergrade",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User changeUserGrade(
        @PathVariable(value = "id") Long id,
        @RequestBody ChangeUserGradeCommand changeUserGradeCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/changeUserGrade  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.changeUserGrade(changeUserGradeCommand);

        userRepository.save(user);
        return user;
    }
}
//>>> Clean Arch / Inbound Adaptor
