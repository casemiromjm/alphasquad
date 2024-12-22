package Game.controller.fighter

import Game.controller.elements.fighter.EnemyControl
import Game.model.elements.Position
import Game.model.elements.fighter.Ally
import Game.model.elements.fighter.Enemy
import Game.model.gameModel.GameModel
import Game.view.GameViewer
import com.googlecode.lanterna.screen.Screen
import spock.lang.Specification

class TestEnemyControl extends Specification{

    def "test selectTarget"(){
        given:
        def enemy = new Enemy(new Position(1, 1))
        GameModel gameModel = Mock()
        def ally1 = new Ally(new Position(1, 10))
        def ally2 = new Ally(new Position(5, 10))
        def targetList = [ally2, ally1]
        Screen screen = Mock()
        GameViewer gameViewer = Mock()

        when:
        def enemyControl = new EnemyControl(gameModel, enemy)
        def target = enemyControl.selectTarget(screen, targetList, gameViewer)

        then:
        target == ally1
    }
}
