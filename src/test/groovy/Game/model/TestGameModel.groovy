package Game.model

import Game.model.gameModel.GameModel
import spock.lang.Specification

class TestGameModel extends Specification {
    Random random
    GameModel gameModel

    def setup(){
        def width = 20
        def height = 10
        random = Mock()

        gameModel = new GameModel(width, height, random)
    }
}
