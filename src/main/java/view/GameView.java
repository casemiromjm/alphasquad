package view;

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

public class GameView {
    private Screen screen;
    private GameModel gameModel;
    private TextGraphics textGraphics;
    private List<Drawable> elements = new ArrayList<>();

    public GameView(Screen screen, GameModel gameModel) {
        this.screen = screen;
        this.gameModel = gameModel;
        textGraphics = screen.newTextGraphics();
        createElements();
    }

    public void drawScenario() throws IOException {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.ANSI.GREEN);

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
