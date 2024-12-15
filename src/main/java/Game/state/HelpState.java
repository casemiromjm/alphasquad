package Game.state;

import Game.controller.HelpScreenController;
import Game.model.HelpScreenModel;
import Game.view.HelpScreenViewer;

public class HelpState extends State {
    public HelpState(HelpScreenModel helpScreenModel, HelpScreenViewer helpScreenViewer, HelpScreenController helpScreenController) {
        super(helpScreenModel, helpScreenViewer, helpScreenController);
    }
}
