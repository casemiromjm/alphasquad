package view;

import com.googlecode.lanterna.screen.Screen;
import model.Model;

import java.io.IOException;

public abstract class Viewer {
    Model model;

    public Viewer(Model model) {
        this.model = model;
    }

    public abstract void draw(Screen screen) throws IOException;
}
