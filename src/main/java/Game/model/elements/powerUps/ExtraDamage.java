package Game.model.elements.powerUps;

import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;

public class ExtraDamage extends PowerUp {
    public ExtraDamage(Position position) {
        super(position);
    }

    //Probably to improve (possibly add an increaseDamage method on the fighter class)
    @Override
    public void apply(Fighter fighter) {
        fighter.increaseDamage(5);
    }
}
