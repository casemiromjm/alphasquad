package Game.view.elements.obstacles;

import Game.model.elements.obstacles.Bush;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BushDraw implements Drawable {
    private final Bush bush;

    public BushDraw(Bush bush) {
        this.bush = bush;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
        textGraphics.putString(new TerminalPosition(bush.getPosition().getX(), bush.getPosition().getY()), "B");
    }
}
