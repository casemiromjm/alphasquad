package Game.state;

import Game.Application;
import com.googlecode.lanterna.screen.Screen;
import Game.controller.Controller;
import model.Model;
import view.Viewer;


import java.io.IOException;

public abstract class State {
    public Model model;
    private Viewer viewer;
    private Controller controller;

    public State(Model model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    public abstract Viewer getViewer();

    public abstract Controller getController();

    public Model getModel(){
        return model;
    }

    public void run(Application application, Screen screen) throws IOException {
        viewer.draw(screen);
        controller.run(application, screen);
    }
}
