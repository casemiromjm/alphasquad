package Game.view.elements.characters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import Game.model.elements.characters.Ally;
import Game.view.elements.Drawable;

public class AllyDraw implements Drawable {
    Ally ally;

    public AllyDraw(Ally ally) {
        this.ally = ally;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        textGraphics.putString(new TerminalPosition(ally.getPosition().getX(), ally.getPosition().getY()), "A");
    }
}
