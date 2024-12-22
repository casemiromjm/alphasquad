package Game;

import Game.controller.MainMenuController;
import Game.model.MainMenuModel;
import Game.state.MainMenuState;
import Game.state.State;
import Game.view.MainMenuViewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    private State state;
    private final ScreenManager screenManager;

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        new Application().start();
    }

    public Application() throws IOException, URISyntaxException, FontFormatException {
        screenManager = new ScreenManager(85, 45, "fonts/square.ttf");
        MainMenuModel mainMenuModel = new MainMenuModel();
        state = new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel));
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Screen getScreen(){
        return screenManager.getScreen();
    }

    private void start() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        while(this.state != null){
            state.run(this, screenManager.getScreen());
        }

        screenManager.closeScreen();
    }

    public long waiting(long time, long timer){
        while(System.currentTimeMillis() - time < timer);
        return System.currentTimeMillis();
    }
}
