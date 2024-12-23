package Game.controller.fighter;

import Game.Application;
import Game.controller.elements.fighter.AllyControl;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.gameModel.GameModel;
import Game.view.GameViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import spock.lang.Specification;

// Classe de testes para o controlo de aliados (AllyControl) utilizando Spock Framework
class TestAllyControl extends Specification {

    /**
     * Testa o movimento básico do aliado com diferentes teclas direcionais.
     */
    def "test basic movement"() {
        given:
        // Configuração inicial do aliado, modelo de jogo e mock de Screen
        def ally = new Ally(new Position(40, 40))
        GameModel gameModel = Mock()
        Screen screen = Mock()
        Application application = Mock()
        gameModel.elementCanBePlaced(_) >> true // Permite colocar elementos em qualquer posição

        when:
        // Criação do controlador de aliados e execução do método de movimento
        AllyControl allyControl = new AllyControl(gameModel, ally)
        allyControl.move(application, screen)

        then:
        // Valida se a entrada de tecla foi lida corretamente e se a posição do aliado foi atualizada
        1 * screen.readInput() >> key
        ally.getPosition().equals(targetPosition)

        where:
        // Define as teclas de entrada e as posições esperadas
        key                                                             |   targetPosition
        new KeyStroke(KeyType.ArrowUp, false, false)                    |   new Position(40, 39)
        new KeyStroke(KeyType.ArrowDown , false, false)                 |   new Position(40, 41)
        new KeyStroke(KeyType.ArrowLeft, false, false)                  |   new Position(39, 40)
        new KeyStroke(KeyType.ArrowRight , false, false)                |   new Position(41, 40)
        new KeyStroke(KeyType.Enter , false, false)                     |   new Position(40, 40)
    }

    /**
     * Testa a seleção de alvos ao mover para a esquerda uma vez.
     */
    def "test targetSelection single Left"() {
        given:
        // Configuração inicial do aliado, inimigos e mock de Screen
        def ally = new Ally(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        // Criação do controlador de aliados e execução da seleção de alvos
        AllyControl allyControl = new AllyControl(gameModel, ally)
        def target = allyControl.selectTarget(screen, targetList, gameViewer)

        then:
        // Simula a entrada de teclas e valida o alvo selecionado
        2 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy2
    }

    /**
     * Testa a seleção de alvos ao mover para a direita uma vez.
     */
    def "test targetSelection single Right"() {
        given:
        def ally = new Ally(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        AllyControl allyControl = new AllyControl(gameModel, ally)
        def target = allyControl.selectTarget(screen, targetList, gameViewer)

        then:
        2 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy2
    }

    /**
     * Testa a seleção de alvos ao mover duas vezes para a direita (voltando ao início da lista).
     */
    def "test targetSelection double Right"() {
        given:
        def ally = new Ally(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        AllyControl allyControl = new AllyControl(gameModel, ally)
        def target = allyControl.selectTarget(screen, targetList, gameViewer)

        then:
        3 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy1
    }

    /**
     * Testa a seleção de alvos ao mover duas vezes para a esquerda (voltando ao final da lista).
     */
    def "test targetSelection double Left"() {
        given:
        def ally = new Ally(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        AllyControl allyControl = new AllyControl(gameModel, ally)
        def target = allyControl.selectTarget(screen, targetList, gameViewer)

        then:
        3 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy1
    }

    /**
     * Testa a seleção de alvos com uma combinação de movimentos esquerda e direita.
     */
    def "test targetSelection mixed"() {
        given:
        def ally = new Ally(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        AllyControl allyControl = new AllyControl(gameModel, ally)
        def target = allyControl.selectTarget(screen, targetList, gameViewer)

        then:
        3 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy1
    }
}

