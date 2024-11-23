import com.googlecode.lanterna.screen.Screen;
import model.GameModel;
import view.GameView;
import controller.GameController;

public class Game {
    private GameController gameController;
    private GameView gameView;
    private GameModel gameModel;
    private Screen screen;

    public Game(Screen screen){
        this.screen = screen;
        gameController = new GameController(screen);
        gameView = new GameView();
        gameModel = new GameModel();
    }

    public void run(){
        // ainda vai ser implementado
    }
}
