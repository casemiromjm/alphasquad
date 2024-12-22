package Game.controller.fighter

import Game.Application
import Game.controller.elements.fighter.PlayerControl
import Game.model.elements.Position
import Game.model.elements.fighter.Enemy
import Game.model.elements.fighter.Player
import Game.model.gameModel.GameModel
import Game.view.GameViewer
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

    def "test targetSelection single Left"(){
        given:
        def player = new Player(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        PlayerControl playerControl = new PlayerControl(gameModel, player)
        def target = playerControl.selectTarget(screen, targetList, gameViewer)

        then:
        2 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy2
    }

    def "test targetSelection single Right"(){
        given:
        def player = new Player(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        PlayerControl playerControl = new PlayerControl(gameModel, player)
        def target = playerControl.selectTarget(screen, targetList, gameViewer)

        then:
        2 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy2
    }

    def "test targetSelection double Right"(){
        given:
        def player = new Player(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        PlayerControl playerControl = new PlayerControl(gameModel, player)
        def target = playerControl.selectTarget(screen, targetList, gameViewer)

        then:
        3 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy1
    }

    def "test targetSelection double Left"(){
        given:
        def player = new Player(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        PlayerControl playerControl = new PlayerControl(gameModel, player)
        def target = playerControl.selectTarget(screen, targetList, gameViewer)

        then:
        3 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy1
    }

    def "test targetSelection mixed"(){
        given:
        def player = new Player(new Position(40, 40))
        GameModel gameModel = Mock()
        GameViewer gameViewer = Mock()
        def enemy1 = new Enemy(new Position(1, 1))
        def enemy2 = new Enemy(new Position(2, 1))
        Screen screen = Mock()
        def targetList = [enemy1, enemy2]

        when:
        PlayerControl playerControl = new PlayerControl(gameModel, player)
        def target = playerControl.selectTarget(screen, targetList, gameViewer)

        then:
        3 * screen.readInput() >>> [new KeyStroke(KeyType.ArrowLeft, false, false),
                                    new KeyStroke(KeyType.ArrowRight, false, false),
                                    new KeyStroke(KeyType.Enter , false, false)]
        target == enemy1
    }
}
