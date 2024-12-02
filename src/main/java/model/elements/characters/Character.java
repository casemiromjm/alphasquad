package model.elements.characters;

import model.elements.Element;
import model.elements.Position;

//The characters of the game, both enemies, friendlies and the player
public abstract class Character extends Element {
    boolean isDead;
    int hitPoints;
    int aim;

    public Character(Position position){
        super(position);
        isDead = false;
        hitPoints = 20;
        aim = 70;       //Subject to change
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
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

    public abstract void selectTarget();

    public abstract void fire();
}
