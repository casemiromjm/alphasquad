package Game.state;

import Game.controller.VictoryController;
import Game.model.VictoryModel;
import Game.view.VictoryViewer;

public class VictoryState extends State {
    public VictoryState(VictoryModel victoryModel, VictoryViewer victoryViewer, VictoryController victoryController) {
        super(victoryModel, victoryViewer, victoryController);
    }
}
