package Game.view;

import Game.model.DefeatModel;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class DefeatViewer extends Viewer {
    public DefeatViewer(DefeatModel defeatModel) {
        super(defeatModel);
    }

    @Override
    public void draw(Screen screen) throws IOException {
        DefeatModel defeatModel = (DefeatModel) super.getModel();
        screen.clear();

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        textGraphics.enableModifiers(SGR.BOLD, SGR.BORDERED);
        textGraphics.putString(new TerminalPosition((screen.getTerminalSize().getColumns() / 2) - (defeatModel.getDefeatMsg().length() / 2),
                        screen.getTerminalSize().getRows()/2),
                        defeatModel.getDefeatMsg());

        screen.refresh();
    }
}
