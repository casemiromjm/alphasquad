package view.elements.characters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.model.elements.characters.Enemy;
import view.elements.Drawable;

public class EnemyDraw implements Drawable {
    Enemy enemy;
    TextGraphics textGraphics;

    public EnemyDraw(TextGraphics textGraphics, Enemy enemy) {
        this.textGraphics = textGraphics;
        this.enemy = enemy;
    }

    public void draw(){
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.putString(new TerminalPosition(enemy.getPosition().getX(), enemy.getPosition().getY()), "E");
    }
}
