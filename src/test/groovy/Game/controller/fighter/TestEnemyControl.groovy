package Game.controller.fighter;

import Game.controller.elements.fighter.EnemyControl;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.gameModel.GameModel;
import Game.view.GameViewer;
import com.googlecode.lanterna.screen.Screen;
import spock.lang.Specification;

// Classe de testes para o controlo de inimigos (EnemyControl) utilizando Spock Framework
class TestEnemyControl extends Specification {

    /**
     * Testa a seleção de alvos para o inimigo.
     * Verifica se o inimigo seleciona corretamente o alvo mais próximo entre uma lista de possíveis alvos.
     */
    def "test selectTarget"() {
        given:
        // Configuração inicial do inimigo, aliados e mocks necessários
        def enemy = new Enemy(new Position(1, 1)) // Inimigo na posição (1,1)
        GameModel gameModel = Mock() // Mock do modelo de jogo
        def ally1 = new Ally(new Position(1, 10)) // Primeiro aliado na posição (1,10)
        def ally2 = new Ally(new Position(5, 10)) // Segundo aliado na posição (5,10)
        def targetList = [ally2, ally1] // Lista de possíveis alvos
        Screen screen = Mock() // Mock do ecrã
        GameViewer gameViewer = Mock() // Mock do visualizador do jogo

        when:
        // Criação do controlador de inimigos e execução do método de seleção de alvos
        def enemyControl = new EnemyControl(gameModel, enemy)
        def target = enemyControl.selectTarget(screen, targetList, gameViewer)

        then:
        // Valida se o alvo selecionado é o mais próximo (ally1)
        target == ally1
    }
}

