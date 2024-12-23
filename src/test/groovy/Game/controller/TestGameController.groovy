package Game.controller;

import Game.Application;
import Game.model.gameModel.GameModel;
import Game.state.VictoryState;
import Game.view.GameViewer;
import com.googlecode.lanterna.screen.Screen;
import spock.lang.Specification;

// Classe de testes para o controlador principal do jogo (GameController) utilizando Spock Framework
class TestGameController extends Specification {
    GameModel gameModel = Mock() // Mock do modelo de jogo
    GameViewer gameViewer = Mock() // Mock do visualizador do jogo
    Application application = Mock() // Mock da aplicação principal
    Screen screen = Mock() // Mock do ecrã

    /**
     * Testa o comportamento do jogo ao atingir o estado de vitória.
     * Verifica se o nível do jogo é maior que 3 e se o estado é alterado para VictoryState.
     */
    def "check victory ending"() {
        when:
        // Criação do controlador do jogo e execução do método run
        GameController gameController = new GameController(gameModel)
        gameController.run(application, screen, gameViewer)

        then:
        // Verifica se o nível do jogo foi consultado e se o estado foi alterado para VictoryState
        1 * gameModel.getLevel() >> 4 // Simula que o nível do jogo é maior que 3
        1 * application.setState(_ as VictoryState) // Verifica se o estado é alterado para VictoryState
    }
}

