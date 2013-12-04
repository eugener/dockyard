package org.dockyard.app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.dockyard.Dockable;
import org.dockyard.DockSite;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private org.dockyard.DockBase dockbase;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dockbase.dock(buildContent("Label"), DockSite.TAB);
        dockbase.dock(buildContent("Left"), DockSite.LEFT);
        dockbase.dock(buildContent("Left 2"), DockSite.LEFT);
        dockbase.dock(buildContent("Right"), DockSite.RIGHT);
        dockbase.dock(buildContent("Right 2"), DockSite.RIGHT);

        dockbase.dock(buildContent("Top"), DockSite.TOP);
        dockbase.dock(buildContent("Top 2"), DockSite.TOP);

        dockbase.dock(buildContent("Bottom"), DockSite.BOTTOM);
        dockbase.dock(buildContent("Bottom 2"), DockSite.BOTTOM);
    }

    private Dockable buildContent(final String title) {
        return new Dockable() {
            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public Node getContent() {
                Label lb = new Label(title);
                lb.setMinSize(100,100);
                return lb;
            }
        };
    }

}
