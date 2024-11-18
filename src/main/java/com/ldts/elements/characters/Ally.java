package com.ldts.elements.characters;

import com.googlecode.lanterna.TextColor;
import com.ldts.elements.Position;

public class Ally extends Character{
    public Ally(String symbol, TextColor color, Position position, String name) {
        super(symbol, color, position, name);
    }

    public Ally(String symbol, TextColor color, Position position, String name, int level) {
        super(symbol, color, position, name, level);
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
