package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class OrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Order>> {

    @Override
    public EntityModel<Order> process(EntityModel<Order> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "requestorder")
                .withRel("equestorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "placeorder")
                .withRel("laceorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/cancelorder")
                .withRel("cancelorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/acceptorder")
                .withRel("acceptorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/rejectorder")
                .withRel("rejectorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/prepareorder")
                .withRel("prepareorder")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/completepreparation"
                )
                .withRel("completepreparation")
        );

        return model;
    }
}
