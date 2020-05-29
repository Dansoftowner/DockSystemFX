package com.dansoftware.dock;

import com.dansoftware.dock.docknode.DockNode;
import com.dansoftware.dock.docksystem.DockSystem;
import com.dansoftware.dock.position.DockPosition;
import com.dansoftware.dock.util.DockTool;
import com.dansoftware.dock.viewmode.event.SceneChangeEventHandler;
import com.dansoftware.dock.viewmode.event.SceneChangedEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class DockSystemTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DockSystem<TabPane> dockSystem = new DockSystem<>((TabPane) createCenter());
        dockSystem.setResourceBundle(DockTool.getDefaultBundle());

        DockNode webDockNode = new DockNode(dockSystem, new ImageView(new Image(getClass().getResourceAsStream("/icons/youtube.png"))), "Youtube");
        webDockNode.setContent(createWebView("http://youtube.com"));
        webDockNode.setDockPosition(DockPosition.LEFT_BOTTOM);
        webDockNode.setOnSceneChanged(event -> {
            new JMetro(Style.DARK).setScene(event.getScene());
            event.getScene().getStylesheets().add(DockTool.DARK_STYLE_SHEET);
        });

        DockNode pdfJSDemo = new DockNode(dockSystem, new ImageView(new Image(getClass().getResourceAsStream("/icons/pdf.png"))), "PDF JS");
        pdfJSDemo.setContent(createWebView("https://mozilla.github.io/pdf.js/es5/web/viewer.html"));
        pdfJSDemo.setDockPosition(DockPosition.RIGHT_BOTTOM);
        pdfJSDemo.setOnSceneChanged(event -> {
            new JMetro(Style.DARK).setScene(event.getScene());
            event.getScene().getStylesheets().add(DockTool.DARK_STYLE_SHEET);
        });

        DockNode faceBookDemo =
                new DockNode(dockSystem, new ImageView(new Image(getClass().getResourceAsStream("/icons/facebook.png"))), "Facebook");
        faceBookDemo.setContent(createWebView("http://www.fb.com"));
        faceBookDemo.setOnSceneChanged(event -> {
            new JMetro(Style.LIGHT).setScene(event.getScene());
            event.getScene().getStylesheets().add(DockTool.LIGHT_STYLE_SHEET);
        });

        DockNode imageDemo = new DockNode(dockSystem, new ImageView(new Image(getClass().getResourceAsStream("/icons/picture.png"))), "Image");
        imageDemo.setContent(createImageView("https://images.unsplash.com/photo-1461749280684-dccba630e2f6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"));
        imageDemo.setDockPosition(DockPosition.BOTTOM_LEFT);

        imageDemo.show();
        faceBookDemo.show();
        webDockNode.show();
        pdfJSDemo.show();

        Scene scene = new Scene(dockSystem);

        new JMetro(Style.LIGHT).setScene(scene);
        scene.getStylesheets().add(DockTool.LIGHT_STYLE_SHEET);

        primaryStage.setScene(scene);
        primaryStage.setTitle("DockSystemFXDemo");
        primaryStage.show();


    }

    private Node createCenter() {
        TabPane tabPane = new TabPane();

        tabPane.getTabs().add(new Tab("Google", createWebView("http://google.com")));
        tabPane.getTabs().add(new Tab("Empty", new StackPane(new Label("Nothing"))));
        tabPane.setMinWidth(200);

        return tabPane;
    }

    private WebView createWebView(String url) {
        WebView webView = new WebView();
        webView.getEngine().load(url);
        return webView;
    }

    private Node createImageView(String url) {
        StackPane node = new StackPane();
        node.setStyle("-fx-background-image: url(" + url +")");

        return node;
    }
}
