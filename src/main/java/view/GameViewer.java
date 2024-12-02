package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.GameModel;
import model.elements.characters.Ally;
import model.elements.characters.Enemy;
import view.elements.Drawable;
import view.elements.characters.AllyDraw;
import view.elements.characters.EnemyDraw;
import view.elements.characters.PlayerDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameViewer extends Viewer {
    private GameModel gameModel;
    private TextGraphics textGraphics;
    private List<Drawable> elements = new ArrayList<>();
    private short animation = 0;

    public GameViewer(Screen screen, GameModel gameModel) {
        super(screen);
        this.gameModel = gameModel;
        textGraphics = screen.newTextGraphics();
    }

    public void draw() throws IOException {
        int width = screen.getTerminalSize().getColumns();
        int height = screen.getTerminalSize().getRows();
        createElements();
        screen.clear();

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
            el.draw();
        }

        screen.refresh();
    }

    private void createElements(){

        elements.add(new PlayerDraw(textGraphics, gameModel.getPlayer()));

        for(Enemy en : gameModel.getEnemyList()){
            elements.add(new EnemyDraw(textGraphics, en));
        }

        for(Ally al : gameModel.getAllyList()){
            elements.add(new AllyDraw(textGraphics, al));
        }
    }
}
