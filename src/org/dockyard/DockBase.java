package org.dockyard;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.input.*;

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

        tabs.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                 /* allow any transfer mode */
                Dragboard db = tabs.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                Tab tab = tabs.getSelectionModel().getSelectedItem();
                content.put(DataFormat.PLAIN_TEXT,  DockUtils.getId(tab));
                db.setContent(content);

                mouseEvent.consume();
            }
        });

        tabs.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != tabs &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });


    }

    /**
     * Dock content to one the possible dock sites
     * @param content
     * @param site
     */
    public void dock(DockContent content, DockSite site) {

        if ( site == DockSite.TAB ) {
            tabs.getTabs().add(DockUtils.buildTab(content));
        } else {
            if ( site != null ) getSite(site).dock(content, DockSite.TAB);
        }

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
