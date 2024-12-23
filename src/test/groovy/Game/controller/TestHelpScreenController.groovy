package Game.controller;

import Game.Application;
import Game.model.HelpScreenModel;
import Game.state.MainMenuState;
import Game.view.HelpScreenViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import spock.lang.Specification;

// Classe de testes para o controlador da tela de ajuda (HelpScreenController) utilizando Spock Framework
class TestHelpScreenController extends Specification {

    /**
     * Testa o comportamento do controlador da tela de ajuda.
     * Verifica se a aplicação retorna ao menu principal após o input de tecla 'q'.
     */
    def "test run"() {
        given:
        // Configuração inicial do modelo de ajuda, visualizador, aplicação e mock do ecrã
        HelpScreenModel helpScreenModel = Mock()
        HelpScreenViewer helpScreenViewer = Mock()
        Application application = Mock()
        Screen screen = Mock()

        when:
        // Criação do controlador de ajuda e execução do método run
        HelpScreenController helpScreenController = new HelpScreenController(helpScreenModel)
        helpScreenController.run(application, screen, helpScreenViewer)

        then:
        // Simula a entrada de duas teclas: 'a' (ignorada) e 'q' (para sair)
        2 * screen.readInput() >>> [new KeyStroke('a' as Character, false, false),
                                    new KeyStroke('q' as Character, false, false)]
        // Verifica se o estado foi alterado para o menu principal
        1 * application.setState(_ as MainMenuState)
    }
}

