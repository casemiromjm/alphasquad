package Game.view.elements.characters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import Game.model.elements.fighter.Enemy;
import Game.view.elements.Drawable;

public class EnemyDraw implements Drawable {
    Enemy enemy;

    public EnemyDraw(Enemy enemy) {
        this.enemy = enemy;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.putString(new TerminalPosition(enemy.getPosition().getX(), enemy.getPosition().getY()), "E");
    }
}
