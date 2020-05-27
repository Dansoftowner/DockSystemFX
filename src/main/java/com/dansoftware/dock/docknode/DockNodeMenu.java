package com.dansoftware.dock.docknode;

import com.dansoftware.dock.position.DockPosition;
import com.dansoftware.dock.viewmode.ViewMode;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.ResourceBundle;


public class DockNodeMenu extends ContextMenu {

    private final DockNode dockNode;

    public DockNodeMenu(DockNode dockNode) {
        this.dockNode = Objects.requireNonNull(dockNode, "The dockTitleBar mustn't be null");
        this.getStyleClass().add("dock-options-menu");
        createDefaultMenuItems();
    }

    private void createDefaultMenuItems() {
        this.getItems().addAll(
                createViewModeMenu(),
                createPositionMenu()
        );
    }

    private Menu createPositionMenu() {
        Menu menu = new Menu(getI18NValue(DockPosition.NAME_LOCALE_KEY));

        ToggleGroup toggleGroup = new ToggleGroup();
        for (DockPosition pos : DockPosition.values()) {
            ImageView graphic = new ImageView();
            graphic.getStyleClass().add(pos.getId());

            RadioMenuItem actualItem = new RadioMenuItem(getI18NValue(pos.getLocaleKey()), graphic);
            actualItem.setToggleGroup(toggleGroup);
            actualItem.setOnAction(e -> this.dockNode.setDockPosition(pos));
            menu.getItems().add(actualItem);

            BooleanBinding selectedProperty = this.dockNode.dockPositionProperty().isEqualTo(pos);
            actualItem.setSelected(selectedProperty.get());
            selectedProperty.addListener((observable, oldValue, newValue) -> actualItem.setSelected(newValue));
        }

        return menu;
    }

    private Menu createViewModeMenu() {
        Menu menu = new Menu(getI18NValue(ViewMode.NAME_LOCALE_KEY));

        ToggleGroup toggleGroup = new ToggleGroup();
        for (ViewMode mode : ViewMode.values()) {
            ImageView graphic = new ImageView();
            graphic.getStyleClass().add(mode.getId());

            RadioMenuItem actualItem = new RadioMenuItem(getI18NValue(mode.getLocaleKey()), graphic);
            actualItem.setToggleGroup(toggleGroup);
            actualItem.setOnAction(event -> this.dockNode.setViewMode(mode));
            menu.getItems().add(actualItem);

            BooleanBinding selectedProperty = this.dockNode.viewModeProperty().isEqualTo(mode);
            actualItem.setSelected(selectedProperty.get());
            selectedProperty.addListener((observable, oldValue, newValue) -> actualItem.setSelected(newValue));
        }

        return menu;
    }

    private String getI18NValue(String key) {
        ResourceBundle resourceBundle = this.dockNode.getDockSystem().getResourceBundle();
        if (resourceBundle == null) {
            return key;
        }

        return resourceBundle.getString(key);
    }
}
