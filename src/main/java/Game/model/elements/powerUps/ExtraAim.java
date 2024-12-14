package Game.model.elements.powerUps;

import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;

public class ExtraAim extends PowerUp {
    public ExtraAim(Position position) {
        super(position);
    }

    @Override
    public void apply(Fighter fighter) {
        fighter.setAim(fighter.getAim() + 10);
    }
}
