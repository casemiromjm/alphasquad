package Game.view.elements.fighter;

import Game.model.elements.fighter.Player;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PlayerDraw implements Drawable {
    Player player;

    public PlayerDraw(Player player) {
        this.player = player;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), "P");
    }
}
