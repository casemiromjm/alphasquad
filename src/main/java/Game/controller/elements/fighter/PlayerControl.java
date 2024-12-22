package Game.controller.elements.fighter;

import Game.Application;
import Game.controller.GameController;
import Game.controller.MainMenuController;
import Game.model.gameModel.GameModel;
import Game.model.MainMenuModel;
import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.fighter.Player;
import Game.sound.SoundPlayer;
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


public class PlayerControl extends GameController implements FighterControl{
    private final Player player;

    public PlayerControl(GameModel gameModel, Player player) {
        super(gameModel);
        this.player = player;
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
        gameModel.applyPowerUps(player);
        gameViewer.draw(screen);
        List<Fighter> enemies = new ArrayList<>(gameModel.getEnemyList());
        Fighter target = selectTarget(screen, enemies, (GameViewer) viewer);
        fire(target);
    }


    @Override
    public void move(Application application, Screen screen) throws IOException {
        boolean moved = false;
        GameModel gameModel = (GameModel) super.getModel();
        Position position = player.getPosition();

        while(!moved){
            KeyStroke keyStroke = screen.readInput();

            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                position = player.getUp();
                moved = gameModel.elementCanBePlaced(position);
            }

            else if(keyStroke.getKeyType() == KeyType.ArrowDown) {
                position = player.getDown();
                moved = gameModel.elementCanBePlaced(position);
            }

            else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                position = player.getRight();
                moved = gameModel.elementCanBePlaced(position);
            }

            else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                position = player.getLeft();
                moved = gameModel.elementCanBePlaced(position);
            }

            else if(keyStroke.getKeyType() == KeyType.Enter){
                position = player.getPosition();
                moved = true;
            }

            else if(keyStroke.getKeyType() == KeyType.Escape) {
                MainMenuModel mainMenuModel = new MainMenuModel();
                application.setState(new MainMenuState(mainMenuModel, new MainMenuViewer(mainMenuModel), new MainMenuController(mainMenuModel)));
                return;
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
    public void fire(Fighter target) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameModel gameModel = (GameModel) super.getModel();
        SoundPlayer soundPlayer = new SoundPlayer();
        if(gameModel.hitOrMiss(player, target)){
            soundPlayer.hitSound();
            target.sufferDamage(gameModel.damageCalculator(player, target.getPosition()));
            return;
        }
        soundPlayer.missSound();
    }
}
