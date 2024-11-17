package com.ldts.elements.obstacles;

import com.ldts.elements.Elements;
import com.ldts.elements.Position;

public abstract class Obstacle extends Elements {
    boolean crossable;

    public Obstacle(char symbol, Position position, boolean crossable) {
        super(symbol, position);
        this.crossable = crossable;
    }

    public boolean isCrossable() {
        return crossable;
    }

    public void setCrossable(boolean crossable) {
        this.crossable = crossable;
    }
}
