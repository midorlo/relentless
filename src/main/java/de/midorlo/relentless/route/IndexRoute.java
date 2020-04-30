package de.midorlo.relentless.route;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import de.midorlo.relentless.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
@PWA(name = "Relentless", shortName = "Relentless")
@Route("")
public class IndexRoute extends AppLayout {

    private Map<Tab, VerticalLayout> contentMap = new HashMap<>();
    private VerticalLayout mainPanel = new IndexChild();

    private     Navigator navigator;
    private LinkedHashMap<String, String> menuItems = new LinkedHashMap<String, String>();

    public IndexRoute(@Autowired PerkService perkService,
                      @Autowired PerkEffectService perkEffectService,
                      @Autowired PerkEffectDescriptionService perkEffectDescriptionService,
                      @Autowired PerkEffectValueService perkEffectValueService) {

        Image img = new Image("https://i.redd.it/pvfjlw904pz01.jpg", "Logo");
        img.setHeight("44px");
        addToNavbar(new DrawerToggle(), img);
        Tab home = new Tab("Home");
        Tab about = new Tab("About");
        Tabs tabs = new Tabs(home, about);
        tabs.addSelectedChangeListener(x -> {
            setContent(new PerksListRoute(perkService,perkEffectService,perkEffectDescriptionService,perkEffectValueService));
        });
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
        setContent(mainPanel);
    }
}

