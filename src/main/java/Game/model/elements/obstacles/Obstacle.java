package Game.model.elements.obstacles;


import Game.model.elements.Element;
import Game.model.elements.Position;

public abstract class Obstacle extends Element {
    private final int damageReduction;
    private final int protection;

    public Obstacle(Position position, int damageReduction, int protection) {
        super(position);
        this.damageReduction = damageReduction;
        this.protection = protection;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public int getProtection() {
        return protection;
    }
}
