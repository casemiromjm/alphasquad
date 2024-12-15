package Game.view;

import Game.model.HelpScreenModel;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public class HelpScreenViewer extends Viewer {

    // Default Constructor
    public HelpScreenViewer(HelpScreenModel helpScreenModel) {
        super(helpScreenModel);
    }

    public void draw(Screen screen) throws IOException {
        HelpScreenModel helpScreenModel = (HelpScreenModel) super.getModel();
        List<String> text = helpScreenModel.getText();
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);

        for(int i = 0; i < text.size(); i++){
            textGraphics.putString(new TerminalPosition(1, i + 1), text.get(i));
        }
        screen.refresh();
    }
}
