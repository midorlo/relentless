package de.midorlo.relentless.ui.route;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.midorlo.relentless.domain.Cell;
import de.midorlo.relentless.service.CellService;
import de.midorlo.relentless.service.PerkEffectDescriptionService;
import de.midorlo.relentless.service.PerkEffectService;
import de.midorlo.relentless.service.PerkEffectValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route("Cells")
public class CellsRoute extends VerticalLayout {


    public CellsRoute(
            @Autowired CellService cellService,
            @Autowired PerkEffectService CellEffectService,
            @Autowired PerkEffectDescriptionService CellEffectDescriptionService,
            @Autowired PerkEffectValueService CellEffectValueService
    ) {
        GridCrud<Cell> crud = new GridCrud<>(Cell.class);
        crud.setFindAllOperation(cellService::all);
        add(crud);
    }
}
