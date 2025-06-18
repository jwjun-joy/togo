package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class DeliveryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Delivery>> {

    @Override
    public EntityModel<Delivery> process(EntityModel<Delivery> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "requestdelivery")
                .withRel("equestdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/acceptdelivery")
                .withRel("acceptdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/rejectdelivery")
                .withRel("rejectdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/startdelivery")
                .withRel("startdelivery")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updatedeliverylocation"
                )
                .withRel("updatedeliverylocation")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/completedelivery"
                )
                .withRel("completedelivery")
        );

        return model;
    }
}
