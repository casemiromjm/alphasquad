package Game.state;

import Game.Application;
import Game.controller.GameController;
import Game.model.gameModel.GameModel;
import Game.state.GameState;
import Game.view.GameViewer;
import com.googlecode.lanterna.screen.Screen;
import spock.lang.Specification;

// Classe de testes para a classe State utilizando Spock Framework.
// Como todas as subclasses de State partilham a mesma implementação do método run, o teste foca-se em GameState.
class TestState extends Specification {

    /**
     * Testa o método run para garantir que as interações entre os componentes de um estado são executadas corretamente.
     */
    def "test run"() {
        // Todos os estados partilham a mesma lógica para o método run.
        given:
        GameModel gameModel = Mock() // Mock do modelo do jogo
        GameViewer gameViewer = Mock() // Mock do visualizador do jogo
        GameController gameController = Mock() // Mock do controlador do jogo
        Application application = Mock() // Mock da aplicação principal
        Screen screen = Mock() // Mock do ecrã

        when:
        // Criação de um estado de jogo (GameState) e execução do método run
        GameState gameState = new GameState(gameModel, gameViewer, gameController)
        gameState.run(application, screen)

        then:
        // Verifica se os métodos esperados foram chamados na ordem correta
        1 * gameViewer.draw(screen) // O visualizador deve desenhar o ecrã
        1 * gameController.run(application, screen, gameViewer) // O controlador deve ser executado
    }
}

