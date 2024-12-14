package Game.state;

import Game.Application;
import com.googlecode.lanterna.screen.Screen;
import Game.controller.Controller;
import Game.model.Model;
import Game.view.Viewer;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class State {
    private Model model;
    private Viewer viewer;
    private Controller controller;

    public State(Model model, Viewer viewer, Controller controller) {
        this.model = model;
        this.viewer = viewer;
        this.controller = controller;
    }

    public Viewer getViewer(){
        return viewer;
    }

    public Controller getController(){
        return controller;
    }

    public Model getModel(){
        return model;
    }

    public void run(Application application, Screen screen) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        viewer.draw(screen);
        controller.run(application, screen, viewer);
    }
}
