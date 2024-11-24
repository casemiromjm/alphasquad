package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.MainMenuModel;

import java.io.IOException;
import java.util.List;

public class MainMenuView {
    private MainMenuModel mainMenuModel;
    private Screen screen;

    public MainMenuView(MainMenuModel mainMenuModel, Screen screen) {
        this.mainMenuModel = mainMenuModel;
        this.screen = screen;
    }

    public void draw() throws IOException {
        //Draw the game title on the screen
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.enableModifiers(SGR.BOLD, SGR.BORDERED);
        textGraphics.putString(new TerminalPosition((screen.getTerminalSize().getColumns() / 2) - (mainMenuModel.getTitle().length() / 2), 3), mainMenuModel.getTitle());

        //Remove the modifiers before drawing the options
        textGraphics.disableModifiers(SGR.BOLD, SGR.BORDERED);
        //Positions the options around the middle of the screen
        int options_row = screen.getTerminalSize().getRows()/2 - 2;
        //Draw the options in the center and bold the first letter
        List<String> options = mainMenuModel.getOptions();

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
