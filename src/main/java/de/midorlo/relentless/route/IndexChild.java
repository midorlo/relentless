package de.midorlo.relentless.route;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class IndexChild extends VerticalLayout {

    public IndexChild() {

        Details component = new Details("Reverse Filled", new Text("Panel content"));
        component.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED);
        add(component);
    }
}
