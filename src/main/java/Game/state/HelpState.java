package Game.state;

import Game.controller.HelpScreenController;
import Game.model.HelpScreenModel;
import Game.view.HelpScreenViewer;

// Classe que representa o estado da ajuda no jogo
public class HelpState extends State {

    /**
     * Construtor que inicializa o estado da ajuda.
     *
     * @param helpScreenModel O modelo que contém os dados específicos do ecrã de ajuda.
     * @param helpScreenViewer O visualizador responsável por renderizar o ecrã de ajuda.
     * @param helpScreenController O controlador que gere as interações no ecrã de ajuda.
     */
    public HelpState(HelpScreenModel helpScreenModel, HelpScreenViewer helpScreenViewer, HelpScreenController helpScreenController) {
        super(helpScreenModel, helpScreenViewer, helpScreenController);
    }
}
