package Game.controller;

import Game.Application;
import Game.model.elements.characters.Fighter;
import Game.view.Viewer;
import com.googlecode.lanterna.screen.Screen;
import Game.model.GameModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        for(Fighter playable : alliesAndPlayer){
            playable.move();
            Fighter target = playable.selectTarget(enemies);
            playable.fire(target);

        }

        for(Fighter enemy : enemies){
            enemy.move();
            Fighter target = enemy.selectTarget(alliesAndPlayer);
            enemy.fire(target);
        }
    }
}
