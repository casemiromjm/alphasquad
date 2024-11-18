package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import model.HelpScreenModel;
import view.HelpScreenView;

import java.io.IOException;

public class HelpScreenController {
    private HelpScreenModel helpScreenModel;
    private HelpScreenView helpScreenView;
    private Screen screen;

    public HelpScreenController(Screen screen) {
        helpScreenModel = new HelpScreenModel();
        helpScreenView = new HelpScreenView(screen);
        this.screen = screen;
    }

    public void run() throws IOException {

        helpScreenView.draw();

        while(true){
            KeyStroke key = screen.readInput();

            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.clear();
                screen.refresh();
                break;
            }
        }
    }
}
