package org.dockyard;

import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Tab;

import java.util.UUID;

public class DockUtils {

    private DockUtils(){}

    private static String PREFIX       = "{dockyard}.";
    private static String CONTENT_ID   = PREFIX + "dockable.id";
    private static String TAB_DOCKABLE = PREFIX + "tab.dockable";


    public static final Tab buildTab(Dockable content) {
        Tab tab = new Tab(content.getTitle());
        tab.setContent(content.getContent());
        tab.getProperties().put(TAB_DOCKABLE,content);
        return tab;
    }

    public static final Dockable getDockable(Node node) {
        return (Dockable) node.getProperties().get(TAB_DOCKABLE);
    }

    /**
     * Get properties from known objects. Currently supported are Node, Tab
     * @param obj
     * @return object properties
     * @throws java.lang.IllegalArgumentException if object types is not supported
     */
    private static ObservableMap<Object, Object> getProperties( Object obj ) {
        if (obj instanceof Node) ((Node)obj).getProperties();
        if (obj instanceof Tab) ((Tab)obj).getProperties();
        throw new IllegalArgumentException("Unknown object type: " + obj.getClass().getName());
    }

    public static final String getId( Object obj ) {
        ObservableMap<Object, Object> props = getProperties(obj);
        String id = (String) props.get(CONTENT_ID);
        if ( id == null ) {
            id = PREFIX + UUID.randomUUID().toString();
            props.put(CONTENT_ID,id);
        }
        return id;
    }

}
