package Game.state;

import Game.Application;
import Game.controller.Controller;
import Game.model.Model;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class State {
    private final Model model;
    private final Viewer viewer;
    private final Controller controller;

    //To later apply the factory method on all states, similar to Hero, to avoid having to create controllers and viewers independently
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
