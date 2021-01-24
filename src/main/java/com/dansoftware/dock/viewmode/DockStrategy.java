package com.dansoftware.dock.viewmode;

import com.dansoftware.dock.docknode.DockNode;
import com.dansoftware.dock.docksystem.DockSystem;
import javafx.stage.Stage;

import java.util.function.Supplier;

public class DockStrategy implements ViewModeStrategy {
    @Override
    public void show(DockNode dockNode, Supplier<Stage> stageFactory) {
        DockSystem<?> dockSystem = dockNode.getDockSystem();
        dockSystem.dock(dockNode.getDockPosition(), dockNode, true);
    }

    @Override
    public void hide(DockNode dockNode) {
        DockSystem<?> dockSystem = dockNode.getDockSystem();
        dockSystem.hide(dockNode.getDockPosition(), dockNode);
    }
}
