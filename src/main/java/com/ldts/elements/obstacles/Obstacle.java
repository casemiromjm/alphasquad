package com.ldts.elements.obstacles;

import com.googlecode.lanterna.TextColor;
import com.ldts.elements.Elements;
import com.ldts.elements.Position;

public abstract class Obstacle extends Elements {
    boolean crossable;

    public Obstacle(String symbol, TextColor color, Position position, boolean crossable) {
        super(symbol, color, position);
        this.crossable = crossable;
    }

    public boolean isCrossable() {
        return crossable;
    }

    public void setCrossable(boolean crossable) {
        this.crossable = crossable;
    }
}
