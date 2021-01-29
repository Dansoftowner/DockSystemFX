package com.dansoftware.dock.docknode;

import com.dansoftware.dock.border.BorderButton;
import com.dansoftware.dock.docksystem.DockSystem;
import com.dansoftware.dock.position.DockPosition;
import com.dansoftware.dock.viewmode.ViewMode;
import com.dansoftware.dock.viewmode.ViewModeStrategy;
import com.dansoftware.dock.viewmode.event.SceneChangeEventHandler;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.function.Supplier;

public class DockNode extends BorderPane {

    private static final String STYLE_CLASS_NAME = "dock-node";

    /**
     * The title-bar of this dock-node
     */
    private final DockTitleBar dockTitleBar;

    /**
     * The button on the border that can make this dockNode visible/invisible
     */
    private final BorderButton borderButton;

    /**
     * The menu that can give access to the user to set the position or
     * the view-mode of this dock-node dynamically
     */
    private final DockNodeMenu menu;

    private ViewModeStrategy viewModeStrategy;

    /**
     * The observable-value that represents the dockSystem that owns this dockNode
     */
    private final SimpleObjectProperty<DockSystem<?>> dockSystem = new SimpleObjectProperty<>(this, "dockSystem");

    /**
     * The observable-value that represents the position of this dockNode
     */
    private final SimpleObjectProperty<DockPosition> dockPosition = new SimpleObjectProperty<>(this, "dockPosition");

    /**
     * The observable-value that represents the view-mode of this dockNode
     */
    private final SimpleObjectProperty<ViewMode> viewMode = new SimpleObjectProperty<>(this, "viewMode", ViewMode.PINNED);

    /***
     * The observable-value that represents the title of this dockNode
     */
    private final StringProperty title = new SimpleStringProperty(this, "name");

    /**
     * The observable-value that represents the graphic of this dockNode
     */
    private final ObjectProperty<Node> graphic = new SimpleObjectProperty<>(this, "graphic");

    private final ObjectProperty<SceneChangeEventHandler> onSceneChanged =
            new SimpleObjectProperty<>(this, "onSceneChange", SceneChangeEventHandler.EMPTY);

    /**
     * The observable-value that represents that this dockNode is visible or not (showed)
     */
    private final BooleanProperty showing = new SimpleBooleanProperty(this, "showing", false);

    private final ObjectProperty<Supplier<Stage>> stageFactory = new SimpleObjectProperty<>(Stage::new);

    public DockNode(DockSystem<?> dockSystem, String title) {
        this.dockSystem.set(dockSystem);
        this.title.set(title);

        this.borderButton = new BorderButton(title);
        this.menu = new DockNodeMenu(this);
        this.dockTitleBar = new DockTitleBar(this);

        this.borderButton.graphicProperty().bind(this.graphic);
        this.borderButton.textProperty().bind(this.title);
        this.borderButton.selectedProperty().bind(this.showing);
        this.borderButton.setContextMenu(this.menu);
        this.borderButton.setOnAction(event -> setShowing(!isShowing()));


        this.viewModeStrategy =
                this.viewMode
                        .get()
                        .getViewModeStrategySupplier()
                        .get();

        this.setTop(this.dockTitleBar);
        this.getStyleClass().add(STYLE_CLASS_NAME);

        this.addListeners();

        SplitPane.setResizableWithParent(this, false);
    }

    public DockNode(DockSystem<?> dockSystem, String title, Node graphic) {
        this(dockSystem, title);
        this.setGraphic(graphic);
    }

    public DockNode(DockSystem<?> dockSystem, String title, Node graphic, Node content) {
        this(dockSystem, title, graphic);
        this.setCenter(content);
    }

    private void addListeners() {
        this.showing.addListener((observable, wasShowing, isShowing) -> {
            if (getDockSystem() == null) return;

            if (isShowing) {
                this.viewModeStrategy.show(this, stageFactory.get());
            } else {
                this.viewModeStrategy.hide(this);
            }
        });

        this.dockPosition.addListener((observable, oldValue, newValue) -> {
            if (getDockSystem() == null) return;

            boolean pinnedViewMode = this.getViewMode() == ViewMode.PINNED;
            boolean isShowingAsDock = this.isShowing() && pinnedViewMode;

            if (oldValue != null && pinnedViewMode)
                this.viewModeStrategy.hide(this);

            getDockSystem().dock(oldValue, newValue, this, isShowingAsDock);
        });

        this.viewMode.addListener((observable, oldValue, newValue) -> {
            ViewModeStrategy strategy = newValue.getViewModeStrategy();

            if (isShowing()) {
                this.hide();
                this.viewModeStrategy = strategy;
                this.show();
            } else this.viewModeStrategy = strategy;
        });

        //----->
        this.onSceneChanged.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                this.onSceneChanged.set(SceneChangeEventHandler.EMPTY);
            }
        });
    }

    public void show() {
        setShowing(true);
    }

    public void hide() {
        setShowing(false);
    }

    public ReadOnlyBooleanProperty showingProperty() {
        return showing;
    }

    public Supplier<Stage> getStageFactory() {
        return stageFactory.get();
    }

    public ObjectProperty<Supplier<Stage>> stageFactoryProperty() {
        return stageFactory;
    }

    public void setStageFactory(Supplier<Stage> stageFactory) {
        this.stageFactory.set(stageFactory);
    }

    public SimpleObjectProperty<DockPosition> dockPositionProperty() {
        return dockPosition;
    }

    public ViewMode getViewMode() {
        return viewMode.get();
    }

    public SimpleObjectProperty<ViewMode> viewModeProperty() {
        return viewMode;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode.set(viewMode);
    }

    public void setShowing(boolean value) {
        this.showing.set(value);
    }

    public DockNodeMenu getMenu() {
        return menu;
    }

    public void setContent(Node value) {
        this.setCenter(value);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public boolean isShowing() {
        return showing.get();
    }

    public DockTitleBar getDockTitleBar() {
        return dockTitleBar;
    }

    public DockSystem<?> getDockSystem() {
        return dockSystem.get();
    }

    public DockPosition getDockPosition() {
        return dockPosition.get();
    }

    public void setDockPosition(DockPosition dockPosition) {
        this.dockPosition.set(dockPosition);
    }

    public void setDockSystem(DockSystem<?> dockSystem) {
        this.dockSystem.set(dockSystem);
    }

    public BorderButton getBorderButton() {
        return borderButton;
    }

    public void setGraphic(Node graphic) {
        this.graphic.set(graphic);
    }

    public Node getGraphic() {
        return graphic.get();
    }

    public ObjectProperty<Node> graphicProperty() {
        return graphic;
    }

    public SceneChangeEventHandler getOnSceneChanged() {
        return onSceneChanged.get();
    }

    public ObjectProperty<SceneChangeEventHandler> onSceneChangedProperty() {
        return onSceneChanged;
    }

    public void setOnSceneChanged(SceneChangeEventHandler onSceneChanged) {
        this.onSceneChanged.set(onSceneChanged);
    }

    public void setGraphicStyleClass(String styleClass) {
        this.borderButton.getGraphic().getStyleClass().add(styleClass);
    }
}
