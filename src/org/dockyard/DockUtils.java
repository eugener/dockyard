package org.dockyard;

import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Tab;

import java.util.UUID;

public class DockUtils {

    private DockUtils(){}

    private static String CONTENT_ID  = "{dockyard}.content.id";
    private static String TAB_CONTENT = "{dockyard}.tab.content";


    public static final Tab buildTab(DockContent content) {
        Tab tab = new Tab(content.getTitle());
        tab.setContent(content.getContent());
        tab.getProperties().put(TAB_CONTENT,content);
        return tab;
    }

    public static final DockContent getContent(Tab tab) {
        return (DockContent) tab.getProperties().get(TAB_CONTENT);
    }

    public static final String getId( Node node ) {

        ObservableMap<Object, Object> props = node.getProperties();
        String id = (String) props.get(CONTENT_ID);
        if ( id == null ) {
            id =  UUID.randomUUID().toString();
            props.put(CONTENT_ID,id);
        }
        return id;
    }

    public static final String getId( Tab tab ) {
        return getId( getContent(tab).getContent());
    }
}
