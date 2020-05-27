package com.dansoftware.dock;

import com.dansoftware.dock.docknode.DockNode;
import com.dansoftware.dock.docksystem.DockSystem;
import com.dansoftware.dock.position.DockPosition;
import com.dansoftware.dock.util.DockTool;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DockSystemTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DockSystem<TabPane> dockSystem = new DockSystem<>((TabPane) createCenter());
        dockSystem.setResourceBundle(DockTool.getDefaultBundle());

        DockNode webDockNode = new DockNode(dockSystem, "Web DockNode");
        webDockNode.setContent(createWebView("http://youtube.com"));
        webDockNode.setDockPosition(DockPosition.LEFT_BOTTOM);

        DockNode pdfJSDemo = new DockNode(dockSystem, "PDF JS");
        pdfJSDemo.setContent(createWebView("https://mozilla.github.io/pdf.js/es5/web/viewer.html"));
        pdfJSDemo.setDockSystem(dockSystem);
        pdfJSDemo.setDockPosition(DockPosition.RIGHT_BOTTOM);

        webDockNode.show();
        pdfJSDemo.show();

        primaryStage.setScene(new Scene(dockSystem));
        primaryStage.setTitle("DockSystemFXDemo");
        primaryStage.show();

        primaryStage.getScene().getStylesheets().add(DockTool.LIGHT_STYLE_SHEET);
    }

    private Node createCenter() {
        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(new Tab("Google", createWebView("http://google.com")));
        tabPane.getTabs().add(new Tab("Empty", new StackPane(new Label("Nothing"))));

        return tabPane;
    }

    private WebView createWebView(String url) {
        WebView webView = new WebView();
        webView.getEngine().load(url);
        return webView;
    }
}
