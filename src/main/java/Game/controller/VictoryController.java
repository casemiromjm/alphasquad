package Game.controller;

import Game.Application;
import Game.model.MainMenuModel;
import Game.model.VictoryModel;
import Game.state.MainMenuState;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class VictoryController extends Controller {
    public VictoryController(VictoryModel victoryModel) {
        super(victoryModel);
    }

    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        long time = System.currentTimeMillis();
        application.waiting(time, 5000);
        MainMenuModel mainMenuModel = new MainMenuModel();
        application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
    }
}
