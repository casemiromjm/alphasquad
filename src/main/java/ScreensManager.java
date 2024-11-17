import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;


public class ScreensManager {

    private Screen screen;


    public ScreensManager() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }

    public void run() throws IOException {
        while(true) {
            MainMenu mainMenu = new MainMenu(screen);

            KeyStroke key = mainMenu.run();

            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.close();
            }

            else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'h') {
                HelpScreen helpScreen = new HelpScreen(screen);
                helpScreen.run();
            }

            else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p'){
                Game game = new Game(screen);
                game.run();
            }

        }
    }
}
