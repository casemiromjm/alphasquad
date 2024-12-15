package Game.view.elements.powerUps;

import Game.model.elements.powerUps.ExtraAim;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ExtraAimDraw implements Drawable {
    ExtraAim extraAim;

    public ExtraAimDraw(ExtraAim extraAim) {
        this.extraAim = extraAim;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
        textGraphics.putString(new TerminalPosition(extraAim.getPosition().getX(), extraAim.getPosition().getY()), "o");
    }
}
