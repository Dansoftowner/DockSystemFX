package com.dansoftware.dock.util;

import java.util.ResourceBundle;

public final class DockTool {

    public static final String DARK_STYLE_SHEET = "/com/dansoftware/dock/dark-style.css";
    public static final String LIGHT_STYLE_SHEET = "/com/dansoftware/dock/light-style.css";

    public static ResourceBundle getDefaultBundle() {
        return ResourceBundle.getBundle("com.dansoftware.dock.DefaultValues");
    }

    private DockTool() {
    }

}
