package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.model.GameModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Fighter;
import Game.view.GameViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class AllyControl extends GameController implements FighterControl {

    private Ally ally;

    public AllyControl(GameModel gameModel, Ally ally) {
        super(gameModel);
        this.ally = ally;
    }
    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException {
        move(screen);
        GameModel gameModel = (GameModel) super.getModel();
        List<Fighter> enemies = new ArrayList<>(gameModel.getEnemyList());
        Fighter target = selectTarget(screen, enemies, (GameViewer) viewer);
        fire(target);
    }

    @Override
    public void move(Screen screen) throws IOException {
        boolean moved = false;

        while(!moved){
            KeyStroke keyStroke = screen.readInput();
            GameModel gameModel = (GameModel) super.getModel();
            Position position;

            switch (keyStroke.getKeyType()){
                case KeyType.ArrowUp:
                    position = new Position(ally.getPosition().getX(), ally.getPosition().getY() - 1);
                    moved = gameModel.elementCanBePlaced(position);
                    break;

                case KeyType.ArrowDown:
                    position = new Position(ally.getPosition().getX(), ally.getPosition().getY() + 1);
                    moved = gameModel.elementCanBePlaced(position);
                    break;

                case KeyType.ArrowRight:
                    position = new Position(ally.getPosition().getX() + 1, ally.getPosition().getY());
                    moved = gameModel.elementCanBePlaced(position);
                    break;

                case KeyType.ArrowLeft:
                    position = new Position(ally.getPosition().getX() - 1, ally.getPosition().getY());
                    moved = gameModel.elementCanBePlaced(position);
                    break;
            }
        }
    }

    @Override
    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException {
        int target_index = 0;
        Fighter target = targets.get(target_index);
        boolean selected = false;

        while(!selected) {
            gameViewer.drawTargetSelection(screen, targets.get(target_index).getPosition());
            KeyStroke keyStroke = screen.readInput();

            switch (keyStroke.getKeyType()) {
                case KeyType.ArrowRight:
                    target_index++;
                    target = targets.get(abs(target_index % targets.size()));
                    break;

                case KeyType.ArrowLeft:
                    target_index--;
                    target = targets.get(abs(target_index % targets.size()));
                    break;

                case KeyType.Enter:
                    selected = true;
            }
        }
        return target;
    }

    //Temporary
    @Override
    public void fire(Fighter target) {
        int realDamage = 0;
        target.setHitPoints(target.getHitPoints() - ally.getDamage());
    }
}
