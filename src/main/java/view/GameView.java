package view;

import com.googlecode.lanterna.screen.Screen;
import model.GameModel;

public class GameView {
    private Screen screen;
    private GameModel gameModel;

    public GameView(Screen screen, GameModel gameModel) {
        this.screen = screen;
        this.gameModel = gameModel;
    }
}
