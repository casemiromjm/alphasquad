package Game.state;

import Game.controller.Controller;
import Game.controller.GameController;
import Game.model.GameModel;
import Game.view.GameViewer;
import Game.view.Viewer;

public class GameState extends State{
    public GameState(GameModel gameModel, GameViewer gameViewer, GameController gameController){
        super(gameModel, gameViewer, gameController);
    }
}
