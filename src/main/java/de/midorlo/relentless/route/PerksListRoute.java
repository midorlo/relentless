package de.midorlo.relentless.route;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import de.midorlo.relentless.domain.Perk;
import de.midorlo.relentless.domain.PerkEffect;
import de.midorlo.relentless.service.PerkEffectDescriptionService;
import de.midorlo.relentless.service.PerkEffectService;
import de.midorlo.relentless.service.PerkEffectValueService;
import de.midorlo.relentless.service.PerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Route("perks")
public class PerksListRoute extends VerticalLayout {

    private final List<Perk> items;

    public PerksListRoute(
            @Autowired PerkService perkService,
            @Autowired PerkEffectService perkEffectService,
            @Autowired PerkEffectDescriptionService perkEffectDescriptionService,
            @Autowired PerkEffectValueService perkEffectValueService
    ) {
        GridCrud<Perk> crud = new GridCrud<>(Perk.class);
        items = new ArrayList<>(perkService.findAll());
        crud.setFindAllOperation(() -> perkService.findAll());
        add(crud);
//
//        Grid<Perk> grid = new Grid<>(Perk.class);
//        grid.setColumns("name", "description");
//
//        grid.addComponentColumn(perk -> {
//
//            List<String> perkEffects = perk.getEffects().stream()
//                    .filter(perkEffect -> perkEffect.getLevel().equals(3))
//                    .map(PerkEffect::getName)
//                    .collect(Collectors.toList());
//
//            ListBox<String> listBox = new ListBox<>();
//            listBox.setItems(perkEffects);
//            listBox.setItems();
//            listBox.setReadOnly(true);
//            return listBox;
//        }).setHeader("Effects");
//
//        grid.setItems(items);
//        grid.setSelectionMode(Grid.SelectionMode.NONE);
//
////        grid.addColumn(new NativeButtonRenderer<>("Details", item -> grid
////                .setDetailsVisible(item, !grid.isDetailsVisible(item))));
//
//
//        add(grid);


    }
}
