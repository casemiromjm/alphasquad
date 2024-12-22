package Game.model.elements.fighter;


import Game.model.elements.Element;
import Game.model.elements.Position;

//The characters of the game, both enemies, friendlies and the player
public abstract class Fighter extends Element {
    int health;
    int aim;
    int damage;

    public Fighter(Position position){
        super(position);
        health = 20;
        aim = 70;
        damage = 10;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }

    public void increaseHealth(int bonus) {
        health += bonus;
    }

    public void sufferDamage(int damage){
        health -= damage;
    }

    public int getAim() {
        return aim;
    }

    public void increaseAim(int bonus) {
        aim += bonus;
    }

    public int getDamage() {
        return damage;
    }

    public void increaseDamage(int bonus) {
        damage += bonus;
    }

    public Position getUp(){
        int new_x = super.getPosition().getX();
        int new_y = super.getPosition().getY() - 1;
        return new Position(new_x, new_y);
    }

    public Position getDown(){
        int new_x = super.getPosition().getX();
        int new_y = super.getPosition().getY() + 1;
        return new Position(new_x, new_y);
    }

    public Position getLeft(){
        int new_x = super.getPosition().getX() - 1;
        int new_y = super.getPosition().getY();
        return new Position(new_x, new_y);
    }

    public Position getRight(){
        int new_x = super.getPosition().getX() + 1;
        int new_y = super.getPosition().getY();
        return new Position(new_x, new_y);
    }
}
