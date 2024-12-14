package Game.view.elements.obstacles;

import Game.model.elements.obstacles.SmallStoneWall;
import Game.view.elements.Drawable;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class SmallStoneWallDraw implements Drawable {
    private SmallStoneWall smallStoneWall;

    public SmallStoneWallDraw(SmallStoneWall smallStoneWall) {
        this.smallStoneWall = smallStoneWall;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#808080"));
        textGraphics.putString(new TerminalPosition(smallStoneWall.getPosition().getX(), smallStoneWall.getPosition().getY()), "S");
    }
}
