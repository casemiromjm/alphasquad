package Game.state;

import Game.controller.MainMenuController;
import Game.model.MainMenuModel;
import Game.view.MainMenuViewer;

// Classe que representa o estado do menu principal no jogo
public class MainMenuState extends State {

    /**
     * Construtor que inicializa o estado do menu principal.
     *
     * @param mainMenuModel O modelo que contém os dados específicos do menu principal.
     * @param mainMenuViewer O visualizador responsável por renderizar o menu principal no ecrã.
     * @param mainMenuController O controlador que gere as interações no menu principal.
     */
    public MainMenuState(MainMenuModel mainMenuModel, MainMenuViewer mainMenuViewer, MainMenuController mainMenuController) {
        super(mainMenuModel, mainMenuViewer, mainMenuController);
    }
}