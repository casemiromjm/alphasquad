import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class ScreensManager {

    private Screen screen;


    public ScreensManager() throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font newfont = font.deriveFont(Font.PLAIN, 18);

        AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(newfont);

        Terminal terminal = new DefaultTerminalFactory()
                .setTerminalEmulatorFontConfiguration(cfg)
                .setForceAWTOverSwing(true).setInitialTerminalSize(new TerminalSize(80, 40))
                .createTerminal();

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
                break;
            }

            else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'h') {
                screen.clear();
                screen.refresh();
                HelpScreen helpScreen = new HelpScreen(screen);
                helpScreen.run();
            }

            else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p'){
                screen.clear();
                screen.refresh();
                Game game = new Game(screen);
                game.run();
            }

            else if (key.getKeyType() == KeyType.EOF) {
                break;
            }

        }
    }
}
