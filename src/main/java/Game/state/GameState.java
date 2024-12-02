package Game.state;

import Game.controller.Controller;
import Game.controller.GameController;
import model.GameModel;
import view.GameViewer;
import view.Viewer;

public class GameState extends State{
    public GameState(GameModel gameModel){
        super(gameModel);
    }

    @Override
    public Viewer getViewer() {
        return new GameViewer((GameModel) super.getModel());
    }

    @Override
    public Controller getController() {
        return new GameController((GameModel) super.getModel());
    }
}
