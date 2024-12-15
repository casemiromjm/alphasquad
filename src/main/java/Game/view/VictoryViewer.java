package Game.view;

import Game.model.VictoryModel;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class VictoryViewer extends Viewer {
    public VictoryViewer(VictoryModel victoryModel) {
        super(victoryModel);
    }

    @Override
    public void draw(Screen screen) throws IOException {
        VictoryModel victoryModel = (VictoryModel) super.getModel();
        screen.clear();

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE_BRIGHT);
        textGraphics.enableModifiers(SGR.BOLD, SGR.BORDERED);
        textGraphics.putString(new TerminalPosition((screen.getTerminalSize().getColumns() / 2) - (victoryModel.getVictoryMsg().length() / 2),
                                                    screen.getTerminalSize().getRows()/2),
                                                    victoryModel.getVictoryMsg());

        screen.refresh();
    }
}
