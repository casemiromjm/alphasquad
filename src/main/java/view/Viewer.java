package view;

import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public abstract class Viewer {
    Screen screen;

    public Viewer(Screen screen) {
        this.screen = screen;
    }

    public abstract void draw() throws IOException;
}
