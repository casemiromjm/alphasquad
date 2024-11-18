import com.googlecode.lanterna.screen.Screen;

import model.GameModel;
import view.GameView;

public class Game {
    private controller.GameController gameController;
    private GameView gameView;
    private GameModel gameModel;
    private Screen screen;

    public Game(Screen screen){
        this.screen = screen;
        gameController = new controller.GameController();
        gameView = new GameView();
        gameModel = new GameModel();
    }

    public void run(){

    }
}
