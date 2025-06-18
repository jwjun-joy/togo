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
// @RequestMapping(value="/riders")
@Transactional
public class RiderController {

    @Autowired
    RiderRepository riderRepository;

    @RequestMapping(
        value = "/riders/{id}/updateriderlocation",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Rider updateRiderLocation(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateRiderLocationCommand updateRiderLocationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /rider/updateRiderLocation  called #####");
        Optional<Rider> optionalRider = riderRepository.findById(id);

        optionalRider.orElseThrow(() -> new Exception("No Entity Found"));
        Rider rider = optionalRider.get();
        rider.updateRiderLocation(updateRiderLocationCommand);

        riderRepository.save(rider);
        return rider;
    }
}
//>>> Clean Arch / Inbound Adaptor
