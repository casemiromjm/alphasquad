package Game.controller;

import Game.Application;
import Game.model.MainMenuModel;
import Game.state.GameState;
import Game.state.HelpState;
import Game.view.MainMenuViewer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import spock.lang.Specification;

// Classe de testes para o controlador do menu principal (MainMenuController) utilizando Spock Framework
class TestMainMenuController extends Specification {

    /**
     * Testa o comportamento do menu principal quando o utilizador seleciona "Quit".
     */
    def "test run with quit"() {
        given:
        MainMenuModel mainMenuModel = Mock()
        MainMenuViewer mainMenuViewer = Mock()
        Application application = Mock()
        Screen screen = Mock()
        TerminalSize terminalSize = Mock()
        screen.getTerminalSize() >> terminalSize
        terminalSize.getColumns() >> 85
        terminalSize.getRows() >> 45

        when:
        MainMenuController mainMenuController = new MainMenuController(mainMenuModel)
        mainMenuController.run(application, screen, mainMenuViewer)

        then:
        1 * screen.readInput() >> new KeyStroke('q' as Character, false, false)
        1 * application.setState(null) // Verifica que o estado é definido como null
        0 * application.setState(_ as GameState)
        0 * application.setState(_ as HelpState)
    }

    /**
     * Testa o comportamento do menu principal quando o utilizador seleciona "Play".
     */
    def "test run with play"() {
        given:
        MainMenuModel mainMenuModel = Mock()
        MainMenuViewer mainMenuViewer = Mock()
        Application application = Mock()
        Screen screen = Mock()
        TerminalSize terminalSize = Mock()
        screen.getTerminalSize() >> terminalSize
        terminalSize.getColumns() >> 85
        terminalSize.getRows() >> 45

        when:
        MainMenuController mainMenuController = new MainMenuController(mainMenuModel)
        mainMenuController.run(application, screen, mainMenuViewer)

        then:
        1 * screen.readInput() >> new KeyStroke('p' as Character, false, false)
        0 * application.setState(null)
        1 * application.setState(_ as GameState) // Verifica que o estado é alterado para GameState
        0 * application.setState(_ as HelpState)
    }

    /**
     * Testa o comportamento do menu principal quando o utilizador seleciona "Help".
     */
    def "test run with help"() {
        given:
        MainMenuModel mainMenuModel = Mock()
        MainMenuViewer mainMenuViewer = Mock()
        Application application = Mock()
        Screen screen = Mock()
        TerminalSize terminalSize = Mock()
        screen.getTerminalSize() >> terminalSize
        terminalSize.getColumns() >> 85
        terminalSize.getRows() >> 45

        when:
        MainMenuController mainMenuController = new MainMenuController(mainMenuModel)
        mainMenuController.run(application, screen, mainMenuViewer)

        then:
        1 * screen.readInput() >> new KeyStroke('h' as Character, false, false)
        0 * application.setState(null)
        0 * application.setState(_ as GameState)
        1 * application.setState(_ as HelpState) // Verifica que o estado é alterado para HelpState
    }

    /**
     * Testa o comportamento do menu principal quando o utilizador envia EOF.
     */
    def "test run with EOF"() {
        given:
        MainMenuModel mainMenuModel = Mock()
        MainMenuViewer mainMenuViewer = Mock()
        Application application = Mock()
        Screen screen = Mock()
        TerminalSize terminalSize = Mock()
        screen.getTerminalSize() >> terminalSize
        terminalSize.getColumns() >> 85
        terminalSize.getRows() >> 45

        when:
        MainMenuController mainMenuController = new MainMenuController(mainMenuModel)
        mainMenuController.run(application, screen, mainMenuViewer)

        then:
        1 * screen.readInput() >> new KeyStroke(KeyType.EOF, false, false)
        1 * application.setState(null) // Verifica que o estado é definido como null
        0 * application.setState(_ as GameState)
        0 * application.setState(_ as HelpState)
    }

    /**
     * Testa o comportamento do menu principal quando o utilizador insere uma tecla inválida.
     */
    def "test run without valid key"() {
        given:
        MainMenuModel mainMenuModel = Mock()
        MainMenuViewer mainMenuViewer = Mock()
        Application application = Mock()
        Screen screen = Mock()
        TerminalSize terminalSize = Mock()
        screen.getTerminalSize() >> terminalSize
        terminalSize.getColumns() >> 85
        terminalSize.getRows() >> 45

        when:
        MainMenuController mainMenuController = new MainMenuController(mainMenuModel)
        mainMenuController.run(application, screen, mainMenuViewer)

        then:
        1 * screen.readInput() >> new KeyStroke('a' as Character, false, false)
        0 * application.setState(null)
        0 * application.setState(_ as GameState)
        0 * application.setState(_ as HelpState) // Verifica que nenhuma alteração de estado ocorre
    }
}

