package Game.state;

import Game.controller.MainMenuController;
import Game.model.MainMenuModel;
import Game.view.MainMenuViewer;

public class MainMenuState extends State{
    public MainMenuState(MainMenuModel mainMenuModel, MainMenuViewer mainMenuViewer, MainMenuController mainMenuController){
        super(mainMenuModel, mainMenuViewer, mainMenuController);
    }
}
