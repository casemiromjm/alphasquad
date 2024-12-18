package Game.controller;

import Game.Application;
import Game.controller.elements.fighter.AllyControl;
import Game.controller.elements.fighter.EnemyControl;
import Game.controller.elements.fighter.PlayerControl;
import Game.model.DefeatModel;
import Game.model.gameModel.GameModel;
import Game.model.VictoryModel;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.state.DefeatState;
import Game.state.GameState;
import Game.state.VictoryState;
import Game.view.DefeatViewer;
import Game.view.VictoryViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class GameController extends Game.controller.Controller {

    public GameController(GameModel gameModel) {
        super(gameModel);
    }

    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();

        if(gameModel.getLevel() > 3){
            VictoryModel victoryModel = new VictoryModel();
            application.setState(new VictoryState(victoryModel, new VictoryViewer(victoryModel), new VictoryController(victoryModel)));
            return;
        }

        PlayerControl playerControl = new PlayerControl(gameModel, gameModel.getPlayer());
        playerControl.run(application, screen, viewer);
        if(!(application.getState() instanceof GameState)){
            return;
        }
        gameModel.cleanDeath();
        if(gameModel.checkNewLevel())                  // If level is over, the turn will also be over
            return;

        for(Ally ally : gameModel.getAllyList()){
            AllyControl allyControl = new AllyControl(gameModel, ally);
            allyControl.run(application, screen, viewer);
            if(!(application.getState() instanceof GameState)){
                return;
            }
            gameModel.cleanDeath();
            if(gameModel.checkNewLevel())              // If level is over, the turn will also be over
                return;
        }

        for(Enemy enemy : gameModel.getEnemyList()){
            EnemyControl enemyControl = new EnemyControl(gameModel, enemy);
            enemyControl.run(application, screen, viewer);
            gameModel.cleanDeath();

            if(gameModel.getPlayer().isDead()){
                DefeatModel defeatModel = new DefeatModel();
                application.setState(new DefeatState(defeatModel, new DefeatViewer(defeatModel), new DefeatController(defeatModel)));
                return;
            }
        }
    }
}
