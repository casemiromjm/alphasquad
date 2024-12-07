package Game.controller.elements.fighter;

import Game.controller.GameController;
import Game.model.GameModel;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.view.GameViewer;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public class EnemyControl extends GameController implements FighterControl {
    private Enemy enemy;

    public EnemyControl(GameModel gameModel, Enemy enemy) {
        super(gameModel);
        this.enemy = enemy;
    }

    public void move(Screen screen) throws IOException{}

    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException{
        return null;
    }

    public void fire(Fighter target){}
}
