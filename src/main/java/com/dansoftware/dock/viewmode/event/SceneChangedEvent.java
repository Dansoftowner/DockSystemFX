package com.dansoftware.dock.viewmode.event;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Scene;

public class SceneChangedEvent extends Event {
    public static final EventType<SceneChangedEvent> ANY = new EventType<>("SCENE_CHANGED");

    private final Scene scene;

    public SceneChangedEvent(Scene scene) {
        super(ANY);
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public static SceneChangedEvent create(Scene scene) {
        return new SceneChangedEvent(scene);
    }
}
