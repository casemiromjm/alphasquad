package Game.controller;

import Game.Application;
import Game.model.Model;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class Controller {
    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException;
}
