package Game.controller.fighter

import Game.Application
import Game.controller.elements.fighter.PlayerControl
import Game.model.elements.Position
import Game.model.elements.fighter.Player
import Game.model.gameModel.GameModel
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

import com.googlecode.lanterna.input.KeyStroke

class TestPlayerControl extends Specification {

    def "test basic movement"(){
        given:
        def player = new Player(new Position(40, 40))
        GameModel gameModel = Mock()
        Screen screen = Mock()
        Application application = Mock()
        gameModel.elementCanBePlaced(_) >> true

        when:
        PlayerControl playerControl = new PlayerControl(gameModel, player)
        playerControl.move(application, screen)

        then:
        1 * screen.readInput() >> key
        player.getPosition().equals(targetPosition)

        where:
        key                                                             |   targetPosition
        new KeyStroke(KeyType.ArrowUp, false, false)                    |   new Position(40, 39)
        new KeyStroke(KeyType.ArrowDown , false, false)                 |   new Position(40, 41)
        new KeyStroke(KeyType.ArrowLeft, false, false)                  |   new Position(39, 40)
        new KeyStroke(KeyType.ArrowRight , false, false)                |   new Position(41, 40)
        new KeyStroke(KeyType.Enter , false, false)                     |   new Position(40, 40)
    }

    def "test basic movement with invalid keys and invalid positions"(){
        given:
        def player = new Player(new Position(40, 40))
        GameModel gameModel = Mock()
        Screen screen = Mock()
        Application application = Mock()

        when:
        PlayerControl playerControl = new PlayerControl(gameModel, player)
        playerControl.move(application, screen)

        then:
        2 * gameModel.elementCanBePlaced(_) >>> [false, true]
        2..3 * screen.readInput() >>> key
        player.getPosition().equals(targetPosition)

        where:
        key                                                                                                                                                 |   targetPosition
        [new KeyStroke(KeyType.ArrowDown, false, false), new KeyStroke('a' as Character, false, false), new KeyStroke(KeyType.ArrowUp, false, false)]       |   new Position(40, 39)
        [new KeyStroke(KeyType.ArrowUp, false, false), new KeyStroke(KeyType.ArrowDown, false, false)]                                                      |   new Position(40, 41)
        [new KeyStroke(KeyType.ArrowRight, false, false), new KeyStroke('b' as Character, false, false), new KeyStroke(KeyType.ArrowLeft, false, false)]    |   new Position(39, 40)
        [new KeyStroke(KeyType.ArrowLeft, false, false), new KeyStroke(KeyType.ArrowRight, false, false)]                                                   |   new Position(41, 40)
    }

}
