package Game.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.MainMenuModel;

import java.io.IOException;


public class MainMenuController extends Game.controller.Controller {

    public MainMenuController(MainMenuModel mainMenuModel) {
        super(mainMenuModel);
    }

    public void run(Application application, Screen screen) throws IOException {

        while (true) {
            KeyStroke key = screen.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                break;

            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'h') {
                screen.clear();
                screen.refresh();
                application.setState(new GameState());

            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {


            } else if (key.getKeyType() == KeyType.EOF) {
                break;
            }
        }
    }
}
