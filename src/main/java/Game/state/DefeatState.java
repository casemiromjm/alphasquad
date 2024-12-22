package Game.state;

import Game.controller.DefeatController;
import Game.model.DefeatModel;
import Game.view.DefeatViewer;

// Classe que representa o estado de derrota no jogo
public class DefeatState extends State {

    /**
     * Construtor que inicializa o estado de derrota.
     *
     * @param defeatModel O modelo que contém os dados específicos do estado de derrota.
     * @param defeatViewer O visualizador responsável por renderizar o estado de derrota no ecrã.
     * @param defeatController O controlador que gere as interações durante o estado de derrota.
     */
    public DefeatState(DefeatModel defeatModel, DefeatViewer defeatViewer, DefeatController defeatController) {
        super(defeatModel, defeatViewer, defeatController);
    }
}

