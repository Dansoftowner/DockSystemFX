package com.dansoftware.dock.viewmode.event;

import javafx.event.EventHandler;

public interface SceneChangeEventHandler extends EventHandler<SceneChangedEvent> {
    SceneChangeEventHandler EMPTY = event -> {
        //do nothing
    };
}
