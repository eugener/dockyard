package org.dockyard;

public enum DockSite {

    TOP(false, true),
    BOTTOM(false, false),
    LEFT(true, true),
    RIGHT(true, false),
    TAB(false, false); // should be last for the index system to work

    private final boolean horizontal;
    private final boolean first;

    private DockSite(boolean horizontal, boolean first) {
        this.horizontal = horizontal;
        this.first = first;
    }

    boolean isHorizontal() {
        return horizontal;
    }

    boolean isFirst() {
        return first;
    }
}
