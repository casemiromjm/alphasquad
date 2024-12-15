package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.controller.MainMenuController;
import Game.model.GameModel;
import Game.model.MainMenuModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Fighter;
import Game.state.GameState;
import Game.state.MainMenuState;
import Game.view.GameViewer;
import Game.view.MainMenuViewer;
import Game.view.Viewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllyControl extends GameController implements FighterControl {

    private final Ally ally;
    private Fighter target;

    public AllyControl(GameModel gameModel, Ally ally) {
        super(gameModel);
        this.ally = ally;
    }
    @Override
    public void run(Application application, Screen screen, Viewer viewer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameModel gameModel = (GameModel) super.getModel();
        GameViewer gameViewer = (GameViewer) viewer;
        gameViewer.draw(screen);
        move(application, screen);
        if(!(application.getState() instanceof GameState)){
            return;
        }
        gameModel.applyPowerUps(ally);
        gameViewer.draw(screen);
        List<Fighter> enemies = new ArrayList<>(gameModel.getEnemyList());
        Fighter target = selectTarget(screen, enemies, (GameViewer) viewer);
        fire(target, gameViewer);
    }

    @Override
    public void move(Application application, Screen screen) throws IOException {
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
                position = ally.getPosition();
                moved = true;
            }

            else if(keyStroke.getKeyType() == KeyType.Escape) {
                MainMenuModel mainMenuModel = new MainMenuModel();
                application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
                return;
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
            gameViewer.drawFighterCombatPhase(screen, ally, targets.get(target_index));
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
    public void fire(Fighter target, GameViewer gameViewer) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameModel gameModel = (GameModel) super.getModel();
        if(gameModel.hitOrMiss(ally, target)){
            gameViewer.hitSound();
            target.setHitPoints(target.getHitPoints() - gameModel.damageCalculator(ally, target.getPosition()));
            return;
        }
        gameViewer.missSound();
    }
}
