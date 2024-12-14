package Game.view.elements.powerUps;

import Game.model.elements.powerUps.ExtraDamage;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ExtraDamageDraw implements Drawable {
    ExtraDamage extraDamage;

    public ExtraDamageDraw(ExtraDamage extraDamage) {
        this.extraDamage = extraDamage;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        textGraphics.putString(new TerminalPosition(extraDamage.getPosition().getX(), extraDamage.getPosition().getY()), ">");
    }
}
