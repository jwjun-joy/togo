package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ReportHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Report>> {

    @Override
    public EntityModel<Report> process(EntityModel<Report> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "createreport")
                .withRel("reatereport")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/changereportstatus"
                )
                .withRel("changereportstatus")
        );

        return model;
    }
}
