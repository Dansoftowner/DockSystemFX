package com.dansoftware.dock.docknode;

import com.dansoftware.dock.docksystem.DockSystem;
import javafx.scene.Node;

public class DockNodeI18N extends DockNode {
    public DockNodeI18N(DockSystem<?> dockSystem, String title) {
        super(dockSystem, dockSystem.getResourceBundle().getString(title));
    }

    public DockNodeI18N(DockSystem<?> dockSystem, Node graphic, String title) {
        super(dockSystem, dockSystem.getResourceBundle().getString(title), graphic);
    }

    public DockNodeI18N(DockSystem<?> dockSystem, Node graphic, String title, Node content) {
        super(dockSystem, dockSystem.getResourceBundle().getString(title), graphic, content);
    }
}
