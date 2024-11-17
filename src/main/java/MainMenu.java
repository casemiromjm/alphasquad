import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu {
    private Screen screen;
    private String title = "Title";
    private List<String> options = Arrays.asList("Play", "Help", "Quit");

    public MainMenu(Screen screen) {
        this.screen = screen;
    }

    public  KeyStroke run() throws IOException {
        draw();
        KeyStroke key = screen.readInput();
        return key;
    }

    private void draw() throws IOException {
        //Draw the game title on the screen
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.enableModifiers(SGR.BOLD, SGR.BORDERED);
        textGraphics.putString(new TerminalPosition((screen.getTerminalSize().getColumns() / 2) - (title.length() / 2), 3), title);

        //Remove the modifiers before drawing the options
        textGraphics.disableModifiers(SGR.BOLD, SGR.BORDERED);
        //com.ldts.elements.Position the options around the middle of the screen
        int options_row = screen.getTerminalSize().getRows()/2 - 2;
        //Draw the options in the center and bold the first letter
        for(String opt:options){
            textGraphics.enableModifiers(SGR.BOLD);
            textGraphics.putString(new TerminalPosition((screen.getTerminalSize().getColumns() / 2) - (opt.length() / 2), options_row), opt.substring(0, 1));
            textGraphics.disableModifiers(SGR.BOLD);
            textGraphics.putString(new TerminalPosition((screen.getTerminalSize().getColumns() / 2) - (opt.length() / 2) + 1, options_row), opt.substring(1));
            options_row += 2;
        }

        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1,screen.getTerminalSize().getRows() - 2), "Note: ");
        textGraphics.disableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(7,screen.getTerminalSize().getRows() - 2),
                "Press the key corresponding to the bolded letter of the option.");

        screen.refresh();
    }
}
