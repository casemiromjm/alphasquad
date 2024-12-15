package Game.model.elements.fighter;


import Game.model.elements.Element;
import Game.model.elements.Position;

//The characters of the game, both enemies, friendlies and the player
public abstract class Fighter extends Element {
    int hitPoints;
    int aim;
    int damage;

    public Fighter(Position position){
        super(position);
        hitPoints = 20;
        aim = 70;       //Subject to change
        damage = 10;
    }

    public boolean isDead() {
        return hitPoints <= 0;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
