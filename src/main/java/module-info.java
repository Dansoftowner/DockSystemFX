module dock.system.fx {

    requires javafx.graphics;
    requires javafx.controls;


    exports com.dansoftware.dock.border;
    exports com.dansoftware.dock.border.toolbar;

    exports com.dansoftware.dock.docknode;
    exports com.dansoftware.dock.docksystem;

    exports com.dansoftware.dock.position;
    exports com.dansoftware.dock.position.strategy;

    exports com.dansoftware.dock.util;

    exports com.dansoftware.dock.viewmode;
    exports com.dansoftware.dock.viewmode.event;
}