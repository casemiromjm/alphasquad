package Game.controller;

import Game.Application;
import Game.view.GameViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;
import Game.model.Model;

import java.io.IOException;

public abstract class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract void run(Application application, Screen screen, Viewer viewer) throws IOException;
}
