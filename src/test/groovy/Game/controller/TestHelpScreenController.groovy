package Game.controller

import Game.Application
import Game.model.HelpScreenModel
import Game.state.MainMenuState
import Game.view.HelpScreenViewer
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class TestHelpScreenController extends Specification {
    def "test run"(){
        given:
        HelpScreenModel helpScreenModel = Mock()
        HelpScreenViewer helpScreenViewer = Mock()
        Application application = Mock()
        Screen screen = Mock()

        when:
        HelpScreenController helpScreenController = new HelpScreenController(helpScreenModel)
        helpScreenController.run(application, screen, helpScreenViewer)

        then:
        2 * screen.readInput() >>> [new KeyStroke('a' as Character, false, false),
                                    new KeyStroke('q' as Character, false, false)]
        1 * application.setState(_ as MainMenuState)
    }
}
