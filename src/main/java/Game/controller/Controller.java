package Game.controller;

import Game.Application;
import com.googlecode.lanterna.screen.Screen;
import model.Model;

import java.io.IOException;

public abstract class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public abstract void run(Application application, Screen screen) throws IOException;
}
