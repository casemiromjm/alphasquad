package Game.controller;

import Game.Application;
import Game.model.HelpScreenModel;
import Game.model.MainMenuModel;
import Game.state.MainMenuState;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class HelpScreenController extends Game.controller.Controller {
    private HelpScreenModel helpScreenModel;
    private Screen screen;

    public HelpScreenController(HelpScreenModel helpScreenModel) {
        super(helpScreenModel);
    }

    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException {
        while(true){
            KeyStroke key = screen.readInput();

            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                MainMenuModel mainMenuModel = new MainMenuModel();
                application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
                break;
            }
        }
    }
}
