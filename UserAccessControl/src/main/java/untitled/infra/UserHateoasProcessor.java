package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class UserHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<User>> {

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/suspenduser")
                .withRel("suspenduser")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/reactivateuser")
                .withRel("reactivateuser")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() + "/changeusergrade"
                )
                .withRel("changeusergrade")
        );

        return model;
    }
}
