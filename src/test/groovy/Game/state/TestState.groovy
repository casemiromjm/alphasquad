package Game.state

import Game.Application
import Game.controller.GameController
import Game.model.gameModel.GameModel
import Game.state.GameState
import Game.view.GameViewer
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class TestState extends Specification{
    def "test run"(){
        //All states share the same run function
        given:
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        GameController gameController = Mock()
        Application application = Mock()
        Screen screen = Mock()

        when:
        GameState gameState = new GameState(gameModel, gameViewer, gameController)
        gameState.run(application, screen)

        then:
        1 * gameViewer.draw(screen)
        1 * gameController.run(application, screen, gameViewer)
    }
}
