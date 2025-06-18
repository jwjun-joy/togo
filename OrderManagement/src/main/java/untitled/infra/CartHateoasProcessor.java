package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class CartHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Cart>> {

    @Override
    public EntityModel<Cart> process(EntityModel<Cart> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "addcartitem")
                .withRel("ddcartitem")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/removecartitem")
                .withRel("removecartitem")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/clearcart")
                .withRel("clearcart")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "checkoutcart")
                .withRel("heckoutcart")
        );

        return model;
    }
}
