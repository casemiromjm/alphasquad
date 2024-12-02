package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.MainMenuModel;
import view.MainMenuViewer;

import java.io.IOException;


public class MainMenuController extends Controller{

    public MainMenuController(MainMenuModel mainMenuModel) {
        super
    }

    public void run() throws IOException {

        while (true) {
            screen.clear();
            mainMenuView.draw();
            KeyStroke key = screen.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                break;

            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'h') {
                screen.clear();
                screen.refresh();
                helpScreenController.run();

            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
                screen.clear();
                screen.refresh();
                gameController.run();

            } else if (key.getKeyType() == KeyType.EOF) {
                break;
            }
        }
    }
}
