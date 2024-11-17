package com.ldts.elements;

//Abstract class for all elements: characters and obstacles
public abstract class Elements {
    char symbol;
    private Position position;

    public Elements(char symbol, Position position) {
        this.symbol = symbol;
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void draw();
}
