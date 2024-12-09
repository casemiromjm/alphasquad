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
        GameViewer gameViewer = (GameViewer) viewer;
        gameViewer.draw(screen);
        GameModel gameModel = (GameModel) super.getModel();
        List<Fighter> enemies = new ArrayList<>(gameModel.getEnemyList());
        Fighter target = selectTarget(screen, enemies, (GameViewer) viewer);
        fire(target);
    }

    @Override
    public void move(Screen screen) throws IOException {
        boolean moved = false;
        GameModel gameModel = (GameModel) super.getModel();
        Position position = ally.getPosition();

        while(!moved){
            KeyStroke keyStroke = screen.readInput();

            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                position = new Position(ally.getPosition().getX(), ally.getPosition().getY() - 1);
                moved = gameModel.elementCanBePlaced(position);
            }

            else if(keyStroke.getKeyType() == KeyType.ArrowDown) {
                position = new Position(ally.getPosition().getX(), ally.getPosition().getY() + 1);
                moved = gameModel.elementCanBePlaced(position);
            }

            else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                position = new Position(ally.getPosition().getX() + 1, ally.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            }

            else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                position = new Position(ally.getPosition().getX() - 1, ally.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            }

            else if(keyStroke.getKeyType() == KeyType.Enter){
                moved = true;
            }
        }

        ally.setPosition(position);
    }

    @Override
    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException {
        int target_index = 0;
        Fighter target = targets.get(target_index);
        boolean selected = false;

        while(!selected) {
            gameViewer.drawFighterCombatPhase(screen, ally, targets.get(target_index).getPosition());
            KeyStroke keyStroke = screen.readInput();

            switch (keyStroke.getKeyType()) {
                case KeyType.ArrowRight:
                    target_index++;
                    break;

                case KeyType.ArrowLeft:
                    target_index--;
                    break;

                case KeyType.Enter:
                    selected = true;
                    break;
            }

            if(target_index < 0){
                target_index += targets.size();
            }
            else if(target_index >= targets.size()){
                target_index -= targets.size();
            }

            target = targets.get(target_index);
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
