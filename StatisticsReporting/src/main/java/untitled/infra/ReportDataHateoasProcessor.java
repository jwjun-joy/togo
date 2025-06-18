package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ReportDataHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ReportData>> {

    @Override
    public EntityModel<ReportData> process(EntityModel<ReportData> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() + "createreportdata"
                )
                .withRel("reatereportdata")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updatereportdata"
                )
                .withRel("updatereportdata")
        );

        return model;
    }
}
