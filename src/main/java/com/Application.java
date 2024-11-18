package com;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        ScreensManager screensManager = new ScreensManager();
        screensManager.run();
    }
}
