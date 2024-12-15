package Game.state;

import Game.controller.DefeatController;
import Game.model.DefeatModel;
import Game.view.DefeatViewer;


public class DefeatState extends State {
    public DefeatState(DefeatModel defeatModel, DefeatViewer defeatViewer, DefeatController defeatController) {
        super(defeatModel, defeatViewer, defeatController);
    }
}
