package Game.model.elements.characters;


import Game.model.elements.Element;
import Game.model.elements.Position;
import com.googlecode.lanterna.screen.Screen;

import java.util.List;

//The characters of the game, both enemies, friendlies and the player
public abstract class Fighter extends Element {
    boolean isDead;
    int hitPoints;
    int aim;

    public Fighter(Position position){
        super(position);
        isDead = false;
        hitPoints = 20;
        aim = 70;       //Subject to change
    }

    public boolean isDead() {
        return isDead;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAim() {
        return aim;
    }

    public void setAim(int aim) {
        this.aim = aim;
    }

    public abstract void move();

    public abstract Fighter selectTarget(List<Fighter> targets);

    public abstract void fire(Fighter target);
}
