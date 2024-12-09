package Game.view;

import Game.model.elements.Position;
import Game.model.elements.fighter.Fighter;
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

    public void drawFighterCombatPhase(Screen screen, Fighter fighter,Position target) throws IOException {
        screen.clear();

        TextGraphics textGraphics = screen.newTextGraphics();
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();

        drawBackground(textGraphics, width, height);
        drawElements(textGraphics);
        drawSideInfo(textGraphics, fighter);
        drawTargetSelection(textGraphics, target);

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

        return elements;
    }

    private void drawBackground(TextGraphics textGraphics, int width, int height){
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.fillRectangle(new TerminalPosition(12, 0), new TerminalSize(width, height), '~');
    }

    private void drawElements(TextGraphics textGraphics){
        List<Drawable> elements = createElements();
        for(Drawable el : elements){
            el.draw(textGraphics);
        }
    }

    private void drawTargetSelection(TextGraphics textGraphics, Position position) {
        textGraphics.setBackgroundColor(TextColor.ANSI.YELLOW);
        textGraphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(1 , 1), ' ');
    }

    private void drawSideInfo(TextGraphics textGraphics, Fighter fighter) {
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(new TerminalPosition(1, 2), "HP: " + fighter.getHitPoints());
        textGraphics.putString(new TerminalPosition(1, 4), "Damage: " + fighter.getDamage());
        textGraphics.putString(new TerminalPosition(1, 6), "Aim: " ); //To update when aim calculation is implemented
    }
}
