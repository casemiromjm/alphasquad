package Game.model.elements.obstacles;


import Game.model.elements.Element;
import Game.model.elements.Position;

public abstract class Obstacle extends Element {
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
