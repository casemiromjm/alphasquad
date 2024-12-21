package Game.controller;

import Game.Application;
import Game.model.gameModel.GameModel;
import Game.model.HelpScreenModel;
import Game.model.MainMenuModel;
import Game.model.gameModel.RandomGameBuilder;
import Game.state.GameState;
import Game.state.HelpState;
import Game.view.GameViewer;
import Game.view.HelpScreenViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Random;


public class MainMenuController extends Game.controller.Controller {

    public MainMenuController(MainMenuModel mainMenuModel) {
        super(mainMenuModel);
    }

    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException {
        KeyStroke key = screen.readInput();

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            application.setState(null);

        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'h') {
            HelpScreenModel helpScreenModel = new HelpScreenModel();
            application.setState(new HelpState(helpScreenModel, new HelpScreenViewer(helpScreenModel), new HelpScreenController(helpScreenModel)));

        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
            int width = screen.getTerminalSize().getColumns();
            int height = screen.getTerminalSize().getRows();
            GameModel gameModel = new GameModel(width, height, 17, new RandomGameBuilder(width, height, 17, new Random()));
            application.setState(new GameState(gameModel, new GameViewer(gameModel), new GameController(gameModel)));

        } else if (key.getKeyType() == KeyType.EOF) {
            application.setState(null);
        }
    }
}
