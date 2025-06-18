package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class PhoneVerificationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PhoneVerification>> {

    @Override
    public EntityModel<PhoneVerification> process(
        EntityModel<PhoneVerification> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "createphoneverification"
                )
                .withRel("reatephoneverification")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/verifyphonenumber"
                )
                .withRel("verifyphonenumber")
        );

        return model;
    }
}
