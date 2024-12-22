package Game.controller

import Game.Application
import Game.model.MainMenuModel
import Game.state.GameState
import Game.state.HelpState
import Game.view.MainMenuViewer
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class TestMainMenuController extends Specification {
    def "test run with quit"(){
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
        1 * screen.readInput() >>  new KeyStroke('q' as Character, false, false)
        1 * application.setState(null)
        0 * application.setState(_ as GameState)
        0 * application.setState(_ as HelpState)
    }

    def "test run with play"(){
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
        1 * screen.readInput() >>  new KeyStroke('p' as Character, false, false)
        0 * application.setState(null)
        1 * application.setState(_ as GameState)
        0 * application.setState(_ as HelpState)
    }

    def "test run with help"(){
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
        1 * screen.readInput() >>  new KeyStroke('h' as Character, false, false)
        0 * application.setState(null)
        0 * application.setState(_ as GameState)
        1 * application.setState(_ as HelpState)
    }

    def "test run with EOF"(){
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
        1 * screen.readInput() >>  new KeyStroke(KeyType.EOF, false, false)
        1 * application.setState(null)
        0 * application.setState(_ as GameState)
        0 * application.setState(_ as HelpState)
    }

    def "test run without valid key"(){
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
        1 * screen.readInput() >>  new KeyStroke('a' as Character, false, false)
        0 * application.setState(null)
        0 * application.setState(_ as GameState)
        0 * application.setState(_ as HelpState)
    }
}
