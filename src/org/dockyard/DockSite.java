package org.dockyard;

public enum DockSite {

    TOP(false, true),
    BOTTOM(false, false),
    LEFT(true, true),
    RIGHT(true, false),
    TAB(false, false);

    private final boolean horizontal;
    private final boolean first;

    private DockSite(boolean horizontal, boolean first) {
        this.horizontal = horizontal;
        this.first = first;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean isFirst() {
        return first;
    }
}
