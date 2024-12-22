package Game.state;

import Game.controller.VictoryController;
import Game.model.VictoryModel;
import Game.view.VictoryViewer;

// Classe que representa o estado de vitória no jogo
public class VictoryState extends State {

    /**
     * Construtor que inicializa o estado de vitória.
     * 
     * @param victoryModel O modelo que contém os dados relacionados ao estado de vitória.
     * @param victoryViewer O visualizador responsável por renderizar o estado de vitória.
     * @param victoryController O controlador que gere as interações durante o estado de vitória.
     */
    public VictoryState(VictoryModel victoryModel, VictoryViewer victoryViewer, VictoryController victoryController) {
        super(victoryModel, victoryViewer, victoryController);
    }
}