package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.model.GameModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.fighter.Player;
import Game.view.GameViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PlayerControl extends GameController implements FighterControl{
    private final Player player;

    public PlayerControl(GameModel gameModel, Player player) {
        super(gameModel);
        this.player = player;
    }

    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException {
        GameViewer gameViewer = (GameViewer) viewer;
        gameViewer.draw(screen);
        move(screen);
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
        Position position = player.getPosition();

        while(!moved){
            KeyStroke keyStroke = screen.readInput();

            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                position = new Position(player.getPosition().getX(), player.getPosition().getY() - 1);
                moved = gameModel.elementCanBePlaced(position);
            }

            else if(keyStroke.getKeyType() == KeyType.ArrowDown) {
                position = new Position(player.getPosition().getX(), player.getPosition().getY() + 1);
                moved = gameModel.elementCanBePlaced(position);
            }

            else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                position = new Position(player.getPosition().getX() + 1, player.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            }

            else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                position = new Position(player.getPosition().getX() - 1, player.getPosition().getY());
                moved = gameModel.elementCanBePlaced(position);
            }

            else if(keyStroke.getKeyType() == KeyType.Enter){
                position = player.getPosition();
                moved = true;
            }
        }

        player.setPosition(position);
    }

    @Override
    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer) throws IOException {
        int target_index = 0;
        Fighter target = targets.get(target_index);
        boolean selected = false;

        while(!selected) {
            gameViewer.drawFighterCombatPhase(screen, player, targets.get(target_index));
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
                target_index %= targets.size();
            }

            target = targets.get(target_index);
        }
        return target;
    }

    //Temporary
    @Override
    public void fire(Fighter target) {
        GameModel gameModel = (GameModel) super.getModel();

        if(gameModel.hitOrMiss(player, target)){
            target.setHitPoints(target.getHitPoints() - gameModel.damageCalculator(player, target.getPosition()));
        }
    }
}
