package Game.controller;

import Game.Application;
import Game.model.GameModel;
import Game.model.HelpScreenModel;
import Game.model.MainMenuModel;
import Game.state.GameState;
import Game.state.HelpState;
import Game.state.MainMenuState;
import Game.view.GameViewer;
import Game.view.HelpScreenViewer;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


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
            GameModel gameModel = new GameModel(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows(), 17);
            application.setState(new GameState(gameModel, new GameViewer(gameModel), new GameController(gameModel)));

        } else if (key.getKeyType() == KeyType.EOF) {
            application.setState(null);
        }
    }
}
