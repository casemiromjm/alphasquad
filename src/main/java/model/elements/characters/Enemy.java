package model.elements.characters;

import model.elements.Position;

public class Enemy extends Character{

    private Position position;

    public Enemy(Position position) {
        super(position);
    }

    public Enemy(Position position,int level) {
        super(position, level);
    }

    @Override
    public void move() {

    }

    @Override
    public void selectTarget() {

    }

    @Override
    public void fire() {

    }

}
