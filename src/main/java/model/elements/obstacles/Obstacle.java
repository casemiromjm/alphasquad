package com.model.elements.obstacles;

import com.googlecode.lanterna.TextColor;
import com.model.elements.Elements;
import com.model.elements.Position;

public abstract class Obstacle extends Elements {
    boolean crossable;

    public Obstacle(Position position, boolean crossable) {
        super(position);
        this.crossable = crossable;
    }

    public boolean isCrossable() {
        return crossable;
    }

    public void setCrossable(boolean crossable) {
        this.crossable = crossable;
    }
}
