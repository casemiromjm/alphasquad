package Game.model.elements.powerUps;

import Game.model.elements.Element;
import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;

public abstract class PowerUp extends Element {

    public PowerUp(Position position) {
        super(position);
    }

    public abstract void apply(Fighter fighter);
}
