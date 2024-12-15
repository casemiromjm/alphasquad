package Game.view.elements.obstacles;

import Game.model.elements.obstacles.SmallWoodenWall;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class SmallWoodenWallDraw implements Drawable {
    private final SmallWoodenWall smallWoodenWall;

    public SmallWoodenWallDraw(SmallWoodenWall smallWoodenWall) {
        this.smallWoodenWall = smallWoodenWall;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#3F301D"));
        textGraphics.putString(new TerminalPosition(smallWoodenWall.getPosition().getX(), smallWoodenWall.getPosition().getY()), "W");
    }
}
