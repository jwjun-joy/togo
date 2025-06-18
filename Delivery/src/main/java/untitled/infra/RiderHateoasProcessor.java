package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class RiderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Rider>> {

    @Override
    public EntityModel<Rider> process(EntityModel<Rider> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updateriderlocation"
                )
                .withRel("updateriderlocation")
        );

        return model;
    }
}
