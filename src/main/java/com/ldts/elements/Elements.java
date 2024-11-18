package com.ldts.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

//Abstract class for all elements: characters and obstacles
public abstract class Elements {
    String symbol;
    TextColor color;
    private Position position;

    public Elements(String symbol, TextColor color, Position position) {
        this.symbol = symbol;
        this.color = color;
        this.position = position;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(color);
        textGraphics.putString(new TerminalPosition(position.getX(), position.getY()), symbol);
    }
}
