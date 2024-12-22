package Game;

import com.googlecode.lanterna.TerminalSize;
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


public class ScreenManager {

    private final Screen screen;

    public ScreenManager(int width, int height, String fontPath) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration cfg = useFont(fontPath);
        Terminal terminal = createTerminal(width, height, cfg);
        screen = createScreen(terminal);
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration cfg) throws IOException {
        Terminal terminal = new DefaultTerminalFactory()
                .setTerminalEmulatorFontConfiguration(cfg)
                .setForceAWTOverSwing(true).setInitialTerminalSize(new TerminalSize(width, height))
                .createTerminal();
        return terminal;
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
        return screen;
    }

    private AWTTerminalFontConfiguration useFont(String fontPath) throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource(fontPath);
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font newfont = font.deriveFont(Font.PLAIN, 18);

        return AWTTerminalFontConfiguration.newInstance(newfont);
    }

    public Screen getScreen(){
        return screen;
    }

    public void closeScreen() throws IOException {
        screen.close();
    }
}
