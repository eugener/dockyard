package org.dockyard;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.dockyard.app.DockContent;

public class DockBase extends SplitPane {

    private TabPane tabs = new TabPane();
    private DockBase[] sides = new DockBase[4];

    private SplitPane hSplit = new SplitPane();


    public DockBase() {

        setOrientation(Orientation.VERTICAL);
        hSplit.setOrientation(Orientation.HORIZONTAL);
        hSplit.getItems().add(tabs);

        getItems().add(hSplit);
    }

    public void dock(DockContent content, DockSite site) {

        switch (site) {
            case TAB: {
                tabs.getTabs().add(buildTab(content.getTitle(), content.getContent()));
                break;
            }
            default: {
                getSide(site).dock(content, DockSite.TAB);
                break;
            }


        }
    }

    private Tab buildTab(String title, Node content) {
        Tab tab = new Tab(title);
        tab.setContent(content);
        return tab;
    }

    private DockBase getSide(DockSite site) {
        DockBase side = sides[site.ordinal()];
        if (side == null) {
            side = new DockBase();
            sides[site.ordinal()] = side;
            ObservableList<Node> items = (site.isHorizontal() ? hSplit : this).getItems();
            if (site.isFirst()) {
                items.add(0, side);
            } else {
                items.add(side);
            }
        }
        return side;
    }


}
