package Game.controller;

import Game.Application;
import Game.controller.elements.fighter.AllyControl;
import Game.controller.elements.fighter.EnemyControl;
import Game.controller.elements.fighter.PlayerControl;
import Game.model.MainMenuModel;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.fighter.Player;
import Game.state.MainMenuState;
import Game.view.GameViewer;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;
import Game.model.GameModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController extends Game.controller.Controller {
    // Default Constructor
    public GameController(GameModel gameModel) {
        super(gameModel);
    }

    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();

        if(gameModel.getLevel() > 3){
            MainMenuModel mainMenuModel = new MainMenuModel();
            application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
            return;
        }

        PlayerControl playerControl = new PlayerControl(gameModel, gameModel.getPlayer());
        playerControl.run(application, screen, viewer);
        gameModel.cleanDeath();
        if(gameModel.checkNewLevel())                  // If level is over, the turn will also be over
            return;

        for(Ally ally : gameModel.getAllyList()){
            AllyControl allyControl = new AllyControl(gameModel, ally);
            allyControl.run(application, screen, viewer);
            gameModel.cleanDeath();
            if(gameModel.checkNewLevel())              // If level is over, the turn will also be over
                return;
        }

        for(Enemy enemy : gameModel.getEnemyList()){
            EnemyControl enemyControl = new EnemyControl(gameModel, enemy);
            enemyControl.run(application, screen, viewer);
            gameModel.cleanDeath();

            if(gameModel.getPlayer().isDead()){
                MainMenuModel mainMenuModel = new MainMenuModel();
                application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
            }
        }
    }
}
