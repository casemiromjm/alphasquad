package model.elements.characters;

import model.elements.Position;

public class Player extends Character{
    public Player(Position position) {
        super(position);
    }

    public Player(Position position, int level) {
        super(position, level);
    }

    @Override
    public void move(){

    }

    @Override
    public void selectTarget() {

    }

    @Override
    public void fire() {

    }

}
