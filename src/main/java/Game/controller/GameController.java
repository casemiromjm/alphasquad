package Game.controller;

import Game.Application;
import Game.controller.elements.fighter.AllyControl;
import Game.controller.elements.fighter.EnemyControl;
import Game.controller.elements.fighter.PlayerControl;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.fighter.Player;
import Game.view.GameViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;
import Game.model.GameModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController extends Game.controller.Controller {
    // Default Constructor
    public GameController(GameModel gameModel) {
        super(gameModel);
    }

    public void run(Application application, Screen screen, Viewer viewer) throws IOException {
        GameModel gameModel = (GameModel) super.getModel();

        List<Fighter> alliesAndPlayer = new ArrayList<>();
        alliesAndPlayer.add(gameModel.getPlayer());
        alliesAndPlayer.addAll(gameModel.getAllyList());
        
        List<Fighter> enemies = new ArrayList<>();
        enemies.addAll(gameModel.getEnemyList());

        ((GameViewer) viewer).drawSideInfo(screen, gameModel.getPlayer());
        PlayerControl playerControl = new PlayerControl(gameModel, gameModel.getPlayer());
        playerControl.run(application, screen, viewer);

        for(Ally ally : gameModel.getAllyList()){
            ((GameViewer) viewer).drawSideInfo(screen, ally);
            AllyControl allyControl = new AllyControl(gameModel, ally);
            allyControl.run(application, screen, viewer);
        }

        for(Enemy enemy : gameModel.getEnemyList()){
            ((GameViewer) viewer).drawSideInfo(screen, enemy);
            EnemyControl enemyControl = new EnemyControl(gameModel, enemy);
            enemyControl.run(application, screen, viewer);
        }
    }
}
