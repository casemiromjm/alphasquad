package Game.state;

import Game.controller.GameController;
import Game.model.gameModel.GameModel;
import Game.view.GameViewer;

public class GameState extends State{
    public GameState(GameModel gameModel, GameViewer gameViewer, GameController gameController){
        super(gameModel, gameViewer, gameController);
    }
}
