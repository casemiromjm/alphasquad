package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.model.GameModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.view.GameViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnemyControl extends GameController implements FighterControl {
    private final Enemy enemy;
    private Fighter target;

    public EnemyControl(GameModel gameModel, Enemy enemy) {
        super(gameModel);
        this.enemy = enemy;
    }

    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();
        List<Fighter> alliesAndPlayer = new ArrayList<>();
        alliesAndPlayer.add(gameModel.getPlayer());
        alliesAndPlayer.addAll(gameModel.getAllyList());

        target = selectTarget(screen, alliesAndPlayer, (GameViewer) viewer);
        GameViewer gameViewer = (GameViewer) viewer;
        long time = System.currentTimeMillis();
        long timer = 1000;

        gameViewer.draw(screen);
        time = application.waiting(time, timer);
        move(application, screen);
        gameViewer.draw(screen);
        time = application.waiting(time, timer);
        gameViewer.drawFighterCombatPhase(screen, enemy, target);
        application.waiting(time, timer);
        fire(target, gameViewer);
    }

    public void move(Application application, Screen screen){
        GameModel gameModel = (GameModel) super.getModel();
        Position currentPosition = enemy.getPosition();
        List<Position> adjacentPositions = Arrays.asList(new Position(currentPosition.getX() + 1, currentPosition.getY()),
                new Position(currentPosition.getX() - 1, currentPosition.getY()),
                new Position(currentPosition.getX(), currentPosition.getY()  + 1),
                new Position(currentPosition.getX(), currentPosition.getY() - 1));

        Position best = null;
        int aim_penalty = 0;     //Senseless value, to change

        for(Position pos : adjacentPositions){
            int pos_aim_penalty = gameModel.aimPenaltyCalculator(pos, target.getPosition());
            if(best == null && gameModel.elementCanBePlaced(pos)){
                best = pos;
                aim_penalty = pos_aim_penalty;
            }

            else if(pos_aim_penalty < aim_penalty && gameModel.elementCanBePlaced(pos)){
                best = pos;
                aim_penalty = pos_aim_penalty;
            }
        }
        enemy.setPosition(best);
    }

    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer){
        GameModel gameModel = (GameModel) super.getModel();
        Fighter closest = targets.getFirst();

        for(Fighter fighter : targets){
            if(gameModel.getDistance(enemy.getPosition(), fighter.getPosition()) < gameModel.getDistance(enemy.getPosition(), closest.getPosition()))
                closest = fighter;

        }
        return closest;
    }

    public void fire(Fighter target, GameViewer gameViewer) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameModel gameModel = (GameModel) super.getModel();

        if(gameModel.hitOrMiss(enemy, target)){
            gameViewer.hitSound();
            target.setHitPoints(target.getHitPoints() - gameModel.damageCalculator(enemy, target.getPosition()));
            return;
        }
        gameViewer.missSound();
    }
}
