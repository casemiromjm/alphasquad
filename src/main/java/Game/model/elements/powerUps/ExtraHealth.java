package Game.model.elements.powerUps;

import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;

public class ExtraHealth extends PowerUp {
    public ExtraHealth(Position position) {
        super(position);
    }

    @Override
    public void apply(Fighter fighter) {
        fighter.increaseHealth(20);
    }
}
