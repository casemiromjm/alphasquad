package Game.state;

import Game.controller.GameController;
import Game.model.gameModel.GameModel;
import Game.view.GameViewer;

// Classe que representa o estado principal do jogo (jogabilidade ativa)
public class GameState extends State {

    /**
     * Construtor que inicializa o estado do jogo.
     *
     * @param gameModel O modelo que contém os dados e lógica específicos do jogo.
     * @param gameViewer O visualizador responsável por renderizar o jogo no ecrã.
     * @param gameController O controlador que gere as interações durante o estado do jogo.
     */
    public GameState(GameModel gameModel, GameViewer gameViewer, GameController gameController) {
        super(gameModel, gameViewer, gameController);
    }
}
