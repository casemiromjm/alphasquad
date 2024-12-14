package Game.view;

import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.obstacles.Bush;
import Game.model.elements.obstacles.Obstacle;
import Game.model.elements.obstacles.SmallStoneWall;
import Game.model.elements.obstacles.SmallWoodenWall;
import Game.model.elements.powerUps.ExtraAim;
import Game.model.elements.powerUps.ExtraDamage;
import Game.model.elements.powerUps.ExtraHealth;
import Game.model.elements.powerUps.PowerUp;
import Game.view.elements.obstacles.BushDraw;
import Game.view.elements.obstacles.SmallStoneWallDraw;
import Game.view.elements.obstacles.SmallWoodenWallDraw;
import Game.view.elements.powerUps.ExtraAimDraw;
import Game.view.elements.powerUps.ExtraDamageDraw;
import Game.view.elements.powerUps.ExtraHealthDraw;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import Game.model.GameModel;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.view.elements.Drawable;
import Game.view.elements.fighter.AllyDraw;
import Game.view.elements.fighter.EnemyDraw;
import Game.view.elements.fighter.PlayerDraw;

import javax.sound.sampled.*;
import java.awt.geom.Arc2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameViewer extends Viewer {

    public GameViewer(GameModel gameModel) {
        super(gameModel);
    }

    public void draw(Screen screen) throws IOException {
        screen.clear();

        TextGraphics textGraphics = screen.newTextGraphics();
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();

        drawBackground(textGraphics, width, height);
        drawElements(textGraphics);

        screen.refresh();
    }

    public void drawFighterCombatPhase(Screen screen, Fighter fighter, Fighter target) throws IOException {
        screen.clear();

        TextGraphics textGraphics = screen.newTextGraphics();
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();

        drawBackground(textGraphics, width, height);
        drawElements(textGraphics);
        drawSideInfo(textGraphics, fighter, target);
        drawTargetSelection(textGraphics, target.getPosition());

        screen.refresh();
    }

    private List<Drawable> createElements(){
        List<Drawable> elements = new ArrayList<>();
        GameModel gameModel = (GameModel) super.getModel();
        elements.add(new PlayerDraw(gameModel.getPlayer()));

        for(Enemy enemy : gameModel.getEnemyList()) {
            elements.add(new EnemyDraw(enemy));
        }

        for(Ally al : gameModel.getAllyList()){
            elements.add(new AllyDraw(al));
        }

        for(Obstacle ob : gameModel.getObstacleList()){
            if(ob instanceof Bush){
                elements.add(new BushDraw((Bush) ob));
            }

            else if(ob instanceof SmallStoneWall){
                elements.add(new SmallStoneWallDraw((SmallStoneWall) ob));
            }

            else if(ob instanceof SmallWoodenWall){
                elements.add(new SmallWoodenWallDraw((SmallWoodenWall) ob));
            }
        }

        for(PowerUp pu : gameModel.getPowerUpList()){
            if(pu instanceof ExtraHealth){
                elements.add(new ExtraHealthDraw((ExtraHealth) pu));
            }

            else if(pu instanceof ExtraAim){
                elements.add(new ExtraAimDraw((ExtraAim) pu));
            }

            else if(pu instanceof ExtraDamage){
                elements.add(new ExtraDamageDraw((ExtraDamage) pu));
            }
        }

        return elements;
    }

    private void drawBackground(TextGraphics textGraphics, int width, int height){
        GameModel gameModel = (GameModel) super.getModel();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.fillRectangle(new TerminalPosition(gameModel.getArenaStartPoint(), 0), new TerminalSize(width, height), '~');
    }

    private void drawElements(TextGraphics textGraphics){

        List<Drawable> elements = createElements();
        for(Drawable el : elements){
            el.draw(textGraphics);
        }
    }

    private void drawTargetSelection(TextGraphics textGraphics, Position position) {
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);
        textGraphics.setCharacter(new TerminalPosition(position.getX(), position.getY()), 'T');
    }

    private void drawSideInfo(TextGraphics textGraphics, Fighter fighter, Fighter target) {
        GameModel gameModel = (GameModel) super.getModel();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);

        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 2), "Active Player");
        textGraphics.disableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 4), "HP: " + fighter.getHitPoints());
        textGraphics.putString(new TerminalPosition(1, 6), "Base Damage: " + fighter.getDamage());
        textGraphics.putString(new TerminalPosition(1, 8), "Real Damage: " + gameModel.damageCalculator(fighter, target.getPosition()));
        textGraphics.putString(new TerminalPosition(1, 10), "Base Aim: " + fighter.getAim());
        textGraphics.putString(new TerminalPosition(1, 12), "Real Aim: " + gameModel.aimCalculator(fighter, target.getPosition())); //To update when aim calculation is implemented

        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 16), "Target");
        textGraphics.disableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(1, 18), "HP: " + target.getHitPoints());
        textGraphics.putString(new TerminalPosition(1, 20), "Damage: " + target.getDamage());
        textGraphics.putString(new TerminalPosition(1, 22), "Aim: " + target.getAim());
    }

    public void hitSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        File soundFile = new File("src/main/resources/sounds/hit.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(1.0f);

        clip.start();
    }

    public void missSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File soundFile = new File("src/main/resources/sounds/miss.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(1.0f);

        clip.start();
    }
}
