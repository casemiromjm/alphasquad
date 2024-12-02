package Game;

import Game.state.MainMenuState;
import Game.state.State;
import com.googlecode.lanterna.screen.Screen;
import model.MainMenuModel;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    private State state;
    private ScreenManager screenManager;

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Application().start();
    }

    public Application() throws IOException, URISyntaxException, FontFormatException {
        screenManager = new ScreenManager();
        state = new MainMenuState(new MainMenuModel());
    }

    public void setState(State state){
        this.state = state;
    }

    public Screen getScreen(){
        return screenManager.getScreen();
    }

    private void start() throws IOException {
        while(this.state != null){
            state.run(this, screenManager.getScreen());
        }

        screenManager.getScreen().close();
    }
}
