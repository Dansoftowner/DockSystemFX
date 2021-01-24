package com.dansoftware.dock.viewmode;

import com.dansoftware.dock.docknode.DockNode;
import javafx.stage.Stage;

import java.util.function.Supplier;

public interface ViewModeStrategy {

    void show(DockNode dockNode, Supplier<Stage> stageFactory);

    void hide(DockNode dockNode);

    default void show(DockNode dockNode) {
        show(dockNode, Stage::new);
    }
}
