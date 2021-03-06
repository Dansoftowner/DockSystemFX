package com.dansoftware.dock.position.strategy;

import com.dansoftware.dock.border.BorderButton;
import com.dansoftware.dock.border.DockFrame;
import com.dansoftware.dock.docknode.DockNode;
import com.dansoftware.dock.docksystem.SplitPaneSystem;

public class TopLeftStrategy implements PositioningStrategy{
    @Override
    public void posDockNode(SplitPaneSystem splitPaneSystem, DockNode dockNode) {
        splitPaneSystem
                .getTopHorizontal()
                .getItems()
                .add(0, dockNode);
    }

    @Override
    public void unPosDockNode(SplitPaneSystem splitPaneSystem, DockNode dockNode) {
        splitPaneSystem
                .getTopHorizontal()
                .getItems()
                .remove(dockNode);
    }

    @Override
    public void posBorderButton(DockFrame frame, BorderButton borderButton) {
        frame.getTopEdge().getToolBar0().addBorderButton(borderButton);
    }

    @Override
    public void unPosBorderButton(DockFrame frame, BorderButton borderButton) {
        frame.getTopEdge().getToolBar0().removeBorderButton(borderButton);
    }
}
