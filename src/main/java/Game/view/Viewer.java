package Game.view;

import Game.model.Model;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public abstract class Viewer {
    private final Model model;

    public Viewer(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract void draw(Screen screen) throws IOException;
}
