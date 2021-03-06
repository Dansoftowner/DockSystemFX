package com.dansoftware.dock.viewmode;

import com.dansoftware.dock.docknode.DockNode;
import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import javafx.css.PseudoClass;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class FloatStrategy extends WindowStrategy {

    private static final PseudoClass FLOAT_PSEUDO_CLASS = PseudoClass.getPseudoClass("float");

    @Override
    public void show(DockNode dockNode) {
        super.show(dockNode);
        dockNode.pseudoClassStateChanged(FLOAT_PSEUDO_CLASS, Boolean.TRUE);
    }

    @Override
    public void hide(DockNode dockNode) {
        super.hide(dockNode);
        dockNode.pseudoClassStateChanged(FLOAT_PSEUDO_CLASS, Boolean.FALSE);
    }

    @Override
    protected Scene getScene(DockNode dockNode) {
        return new BorderlessSceneImpl(dockNode);
    }

    @Override
    protected Window getOwner(DockNode dockNode) {
        return dockNode.getDockSystem().getScene().getWindow();
    }

    private class BorderlessSceneImpl extends BorderlessScene {
        public BorderlessSceneImpl(DockNode dockNode) {
            super(getFloatingStage(), StageStyle.UNDECORATED, new StackPane(dockNode), 100, 100);
            this.removeDefaultCSS();
            this.setMoveControl(dockNode.getDockTitleBar());
            this.snapProperty().set(false);
        }
    }
}
