package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.model.gameModel.GameModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.model.gameModel.GameUtils;
import Game.sound.SoundPlayer;
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
        application.waiting(time, timer);
        gameViewer.drawFighterCombatPhase(screen, enemy, target);
        fire(target);
    }

    public void move(Application application, Screen screen){
        GameModel gameModel = (GameModel) super.getModel();
        GameUtils gameUtils = new GameUtils(gameModel);
        List<Position> adjacentPositions = Arrays.asList(enemy.getUp(), enemy.getDown(), enemy.getLeft(), enemy.getRight());

        Position best = null;
        int aim_penalty = 0;

        for(Position pos : adjacentPositions){
            int pos_aim_penalty = gameUtils.aimPenaltyCalculator(pos, target.getPosition());
            if(best == null && gameUtils.elementCanBePlaced(pos)){
                best = pos;
                aim_penalty = pos_aim_penalty;
            }

            else if(pos_aim_penalty < aim_penalty && gameUtils.elementCanBePlaced(pos)){
                best = pos;
                aim_penalty = pos_aim_penalty;
            }
        }
        enemy.setPosition(best);
    }

    public Fighter selectTarget(Screen screen, List<Fighter> targets, GameViewer gameViewer){
        Fighter closest = targets.getFirst();

        for(Fighter fighter : targets){
            if(Position.getDistance(enemy.getPosition(), fighter.getPosition()) < Position.getDistance(enemy.getPosition(), closest.getPosition()))
                closest = fighter;

        }
        return closest;
    }

    public void fire(Fighter target) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameModel gameModel = (GameModel) super.getModel();
        GameUtils gameUtils = new GameUtils(gameModel);
        SoundPlayer soundPlayer = new SoundPlayer();
        if(gameModel.hitOrMiss(enemy, target)){
            soundPlayer.hitSound();
            target.sufferDamage(gameUtils.damageCalculator(enemy, target.getPosition()));
            return;
        }
        soundPlayer.missSound();
    }
}
