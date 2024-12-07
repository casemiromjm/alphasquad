package Game.view;

import com.googlecode.lanterna.screen.Screen;
import Game.model.Model;

import java.io.IOException;

public abstract class Viewer {
    private Model model;

    public Viewer(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract void draw(Screen screen) throws IOException;
}
