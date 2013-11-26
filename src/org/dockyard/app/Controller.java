package org.dockyard.app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.dockyard.DockSite;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private org.dockyard.DockBase dockbase;


    @FXML
    protected void initialize() {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }

    private DockContent buildContent(final String title) {
        return new DockContent() {
            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public Node getContent() {
                return new Label(title);
            }
        };
    }

}