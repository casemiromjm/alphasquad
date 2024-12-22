package Game.controller

import Game.Application
import Game.model.gameModel.GameModel
import Game.state.VictoryState
import Game.view.GameViewer
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class TestGameController extends Specification {
    GameModel gameModel = Mock()
    GameViewer gameViewer = Mock()
    Application application = Mock()
    Screen screen = Mock()

    def "check victory ending"(){
        when:
        GameController gameController = new GameController(gameModel)
        gameController.run(application, screen, gameViewer)

        then:
        1 * gameModel.getLevel() >> 4
        1 * application.setState(_ as VictoryState)
    }
}
