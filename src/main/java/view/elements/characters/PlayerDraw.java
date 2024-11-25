package view.elements.characters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.elements.characters.Player;
import view.elements.Drawable;

public class PlayerDraw implements Drawable {
    Player player;
    TextGraphics textGraphics;

    public PlayerDraw(TextGraphics textGraphics, Player player) {
        this.player = player;
        this.textGraphics = textGraphics;
    }

    public void draw(){
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), "P");
    }
}
