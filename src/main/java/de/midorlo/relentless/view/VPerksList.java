package de.midorlo.relentless.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;
import de.midorlo.relentless.domain.perk.Perk;
import de.midorlo.relentless.domain.perk.PerkEffect;
import de.midorlo.relentless.service.PerkEffectDescriptionService;
import de.midorlo.relentless.service.PerkEffectService;
import de.midorlo.relentless.service.PerkEffectValueService;
import de.midorlo.relentless.service.PerkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Route("perks")
public class VPerksList extends VerticalLayout {

    private final List<Perk> items;

    public VPerksList(
            @Autowired PerkService perkService,
            @Autowired PerkEffectService perkEffectService,
            @Autowired PerkEffectDescriptionService perkEffectDescriptionService,
            @Autowired PerkEffectValueService perkEffectValueService
    ) {
        items = new ArrayList<>(perkService.findAll());
        Grid<Perk> grid = new Grid<>(Perk.class);
        grid.setColumns("name", "description");

        grid.addComponentColumn(perk -> {

            List<String> perkEffects = perk.getEffects().stream()
                    .filter(perkEffect -> perkEffect.getLevel().equals(3))
                    .map(PerkEffect::getName)
                    .collect(Collectors.toList());

            ListBox<String> listBox = new ListBox<>();
            listBox.setItems(perkEffects);
            listBox.setItems();
            listBox.setReadOnly(true);
            return listBox;
        }).setHeader("Effects");

        grid.setItems(items);
        grid.setSelectionMode(Grid.SelectionMode.NONE);

//        grid.addColumn(new NativeButtonRenderer<>("Details", item -> grid
//                .setDetailsVisible(item, !grid.isDetailsVisible(item))));


        add(grid);


    }
}
