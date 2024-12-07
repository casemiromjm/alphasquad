package Game.model.elements.characters;

import Game.model.elements.Position;
import com.googlecode.lanterna.screen.Screen;

import java.util.List;

public class Player extends Fighter {
    public Player(Position position) {
        super(position);
    }

    @Override
    public void move(){

    }

    @Override
    public Fighter selectTarget(List<Fighter> targets) {
    }

    @Override
    public void fire(Fighter target) {

    }

}
