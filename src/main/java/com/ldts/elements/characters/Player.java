package com.ldts.elements.characters;

import com.ldts.elements.Position;

public class Player extends Character{
    public Player(char symbol, Position position, String name) {
        super(symbol, position, name);
    }

    public Player(char symbol, Position position, String name, int level) {
        super(symbol, position, name, level);
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


    @Override
    public void draw() {

    }
}
