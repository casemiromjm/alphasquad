package controller;

import com.googlecode.lanterna.screen.Screen;
import model.GameModel;
import view.GameView;

import java.io.IOException;

public class GameController {

    // members
    private Screen screen;
    private GameModel gameModel;
    private GameView gameView;

    // Default Constructor
    public GameController(Screen screen) {
        this.screen = screen;
        gameModel = new GameModel(1, screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows());
        gameView = new GameView(screen, gameModel);
    }

    public void run() throws IOException {
        gameModel.createInitialElements();
        gameView.drawScenario();
    }
}
