package Game.view.elements.powerUps;

import Game.model.elements.powerUps.ExtraHealth;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ExtraHealthDraw implements Drawable {
    ExtraHealth extraHealth;

    public ExtraHealthDraw(ExtraHealth extraHealth) {
        this.extraHealth = extraHealth;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
        textGraphics.putString(new TerminalPosition(extraHealth.getPosition().getX(), extraHealth.getPosition().getY()), "+");
    }
}
