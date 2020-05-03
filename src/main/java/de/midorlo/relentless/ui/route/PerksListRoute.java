package de.midorlo.relentless.ui.route;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.service.PerkEffectDescriptionService;
import de.midorlo.relentless.service.PerkEffectService;
import de.midorlo.relentless.service.PerkEffectValueService;
import de.midorlo.relentless.service.PerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route("perks")
public class PerksListRoute extends VerticalLayout {


    public PerksListRoute(
            @Autowired PerkService perkService,
            @Autowired PerkEffectService perkEffectService,
            @Autowired PerkEffectDescriptionService perkEffectDescriptionService,
            @Autowired PerkEffectValueService perkEffectValueService
    ) {
        GridCrud<Perk> crud = new GridCrud<>(Perk.class);
        crud.setFindAllOperation(perkService::findAll);
        add(crud);
    }
}
