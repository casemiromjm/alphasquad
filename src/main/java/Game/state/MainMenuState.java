package Game.state;

import Game.controller.Controller;
import Game.controller.MainMenuController;
import model.MainMenuModel;
import view.MainMenuViewer;
import view.Viewer;

public class MainMenuState extends State{
    public MainMenuState(MainMenuModel mainMenuModel){
        super(mainMenuModel);
    }

    @Override
    public Viewer getViewer() {
        return new MainMenuViewer((MainMenuModel) super.getModel());
    }

    @Override
    public Controller getController() {
        return new MainMenuController((MainMenuModel) super.getModel());
    }
}
