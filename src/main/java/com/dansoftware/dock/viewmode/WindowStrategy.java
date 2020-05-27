package com.dansoftware.dock.viewmode;

import com.dansoftware.dock.docknode.DockNode;
import com.dansoftware.dock.viewmode.event.SceneChangedEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WindowStrategy implements ViewModeStrategy {

    private Stage floatingStage;
    private Scene scene;

    @Override
    public void show(DockNode dockNode) {
        floatingStage = new Stage();
        scene = getScene(dockNode);
        dockNode.getOnSceneChanged().handle(SceneChangedEvent.create(scene));

        floatingStage.setScene(scene);
        floatingStage.initOwner(getOwner(dockNode));
        floatingStage.titleProperty().bind(dockNode.titleProperty());
        floatingStage.setOnCloseRequest(event -> {
            dockNode.hide();
            event.consume();
        });
        floatingStage.show();
    }

    @Override
    public void hide(DockNode dockNode) {
        Pane root = (Pane) scene.getRoot();
        root.getChildren().remove(dockNode);
        floatingStage.close();
        floatingStage = null;
    }

    protected Scene getScene(DockNode dockNode) {
        return new Scene(new StackPane(dockNode));
    }

    protected final Stage getFloatingStage() {
        return floatingStage;
    }

    protected Window getOwner(DockNode dockNode) {
        return null;
    }
}
