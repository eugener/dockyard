package org.dockyard;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class DockBase extends SplitPane {

    private TabPane tabs = new TabPane();
    private DockBase[] sites = new DockBase[DockSite.values().length-1];
    private SplitPane hSplit = new SplitPane();


    public DockBase() {

        setOrientation(Orientation.VERTICAL);
        setDividerPositions(100,100);

        hSplit.setOrientation(Orientation.HORIZONTAL);
        hSplit.setDividerPositions(100,100);

        hSplit.getItems().add(tabs);

        getItems().add(hSplit);
    }

    /**
     * Dock content to one the possible dock sites
     * @param content
     * @param site
     */
    public void dock(DockContent content, DockSite site) {

        if ( site == DockSite.TAB ) {
            tabs.getTabs().add(buildTab(content.getTitle(), content.getContent()));
        } else {
            if ( site != null ) getSite(site).dock(content, DockSite.TAB);
        }

    }

    private Tab buildTab(String title, Node content) {
        Tab tab = new Tab(title);
        tab.setContent(content);
        return tab;
    }

    private DockBase getSite(DockSite site) {
        DockBase side = sites[site.ordinal()];
        if (side == null) {
            side = new DockBase();
            sites[site.ordinal()] = side;
            List<Node> items = (site.isHorizontal() ? hSplit : this).getItems();
            if (site.isFirst()) {
                items.add(0, side);
            } else {
                items.add(side);
            }
        }
        return side;
    }


}
