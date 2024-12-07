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
import Game.view.elements.characters.AllyDraw;
import Game.view.elements.characters.EnemyDraw;
import Game.view.elements.characters.PlayerDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameViewer extends Viewer {
    private List<Drawable> elements = new ArrayList<>();
    private short animation = 0;

    public GameViewer(GameModel gameModel) {
        super(gameModel);
    }

    public void draw(Screen screen) throws IOException {

        TextGraphics textGraphics = screen.newTextGraphics();
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();
        createElements();
        screen.clear();

        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);

        switch(animation){

        case 0:
            textGraphics.fillRectangle(new TerminalPosition(11, 0), new TerminalSize(width, height), '~');
            animation = 1;
            break;

        case 1:
            textGraphics.fillRectangle(new TerminalPosition(11, 0), new TerminalSize(width, height), '^');
            animation = 0;
            break;
        }

        for(Drawable el : elements){
            el.draw(textGraphics);
        }

        screen.refresh();
    }

    private void createElements(){
        GameModel gameModel = (GameModel) super.getModel();
        elements.add(new PlayerDraw(gameModel.getPlayer()));

        for(Enemy enemy : gameModel.getEnemyList()) {
            elements.add(new EnemyDraw(enemy));
        }

        for(Ally al : gameModel.getAllyList()){
            elements.add(new AllyDraw(al));
        }
    }

    public void drawTargetSelection(Screen screen, Position position) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.ANSI.YELLOW);
        textGraphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(1 , 1), ' ');
        screen.refresh();
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
    }

    public void drawSideInfo(Screen screen, Fighter fighter) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(new TerminalPosition(2, 2), "HP: " + fighter.getHitPoints());
        textGraphics.putString(new TerminalPosition(2, 4), "Damage: " + fighter.getDamage());
        updateAimInfo(screen, fighter.getAim());
        screen.refresh();
    }

    public void updateAimInfo(Screen screen, int aim) throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(new TerminalPosition(2, 6), "Aim: " + aim);
        screen.refresh();
    }
}
