package view.elements.characters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.elements.characters.Ally;
import view.elements.Drawable;

public class AllyDraw implements Drawable {
    Ally ally;
    TextGraphics textGraphics;

    public AllyDraw(Ally ally, TextGraphics textGraphics) {
        this.ally = ally;
        this.textGraphics = textGraphics;
    }

    public void draw(){
        textGraphics.setForegroundColor(TextColor.ANSI.CYAN);
        textGraphics.putString(new TerminalPosition(ally.getPosition().getX(), ally.getPosition().getY()), "E");
    }
}
