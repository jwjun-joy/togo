package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class RestaurantHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Restaurant>> {

    @Override
    public EntityModel<Restaurant> process(EntityModel<Restaurant> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "createmenu")
                .withRel("reatemenu")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/updatemenu")
                .withRel("updatemenu")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deletemenu")
                .withRel("deletemenu")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updaterestaurantinfo"
                )
                .withRel("updaterestaurantinfo")
        );

        return model;
    }
}
