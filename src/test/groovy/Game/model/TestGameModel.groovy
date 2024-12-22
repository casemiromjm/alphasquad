package Game.model

import Game.model.elements.Position
import Game.model.elements.fighter.Ally
import Game.model.elements.fighter.Enemy
import Game.model.elements.fighter.Player
import Game.model.elements.obstacles.Bush
import Game.model.elements.obstacles.Obstacle
import Game.model.elements.obstacles.SmallStoneWall
import Game.model.elements.obstacles.SmallWoodenWall
import Game.model.elements.powerUps.ExtraAim
import Game.model.elements.powerUps.ExtraHealth
import Game.model.elements.powerUps.PowerUp
import Game.model.gameModel.GameBuilder
import Game.model.gameModel.GameModel
import spock.lang.Shared
import spock.lang.Specification

import static java.lang.Math.round
import static java.lang.Math.toIntExact

class TestGameModel extends Specification {

    @Shared
    def width = 50
    @Shared
    def height = 25
    @Shared
    def arenaStartPoint = 2


    def "testing elements calling creation (interactions)"() {
        given:
        GameBuilder gameBuilder = Mock()

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)

        then:
        1 * gameBuilder.createPlayer()
        1 * gameBuilder.createAllies(_)
        1 * gameBuilder.createEnemies(_)
        1 * gameBuilder.createObstacles(_)
        1 * gameBuilder.createPowerUps(_)
    }

    def "testing elements storing after creation"() {
        given:
        //Simplified build for testing purposes (PowerUps are irrelevant for this
        def gameBuilder = Mock(GameBuilder)
        gameBuilder.createPlayer() >> new Player(new Position(width.intdiv(2), height - 2))
        gameBuilder.createAllies(_) >> [new Ally(new Position(width.intdiv(2) - 1, height - 2))]
        gameBuilder.createEnemies(_) >> [new Enemy(new Position(4, 2))]
        gameBuilder.createObstacles(_) >> [new Bush(new Position(5, 5)), new SmallStoneWall(new Position(7, 5)), new SmallWoodenWall(new Position(9, 5))]
        gameBuilder.createPowerUps(_) >> [new ExtraHealth(new Position(9, 9))]

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        Player player = gameModel.getPlayer()
        List<Ally> allyList = gameModel.getAllyList()
        List<Enemy> enemyList = gameModel.getEnemyList()
        List<Obstacle> obstacleList = gameModel.getObstacleList()
        List<PowerUp> powerUpList = gameModel.getPowerUpList()

        then:
        //If the return values from the functions were not used, the following elements would be null and lists would be empty
        player != null
        !allyList.isEmpty()
        !enemyList.isEmpty()
        !obstacleList.isEmpty()
        !powerUpList.isEmpty()
    }

    def "testing elementCanBePlaced"() {

        given:
        //Simplified build for testing purposes (PowerUps are irrelevant for this
        def gameBuilder = Mock(GameBuilder)
        gameBuilder.createPlayer() >> new Player(new Position(width.intdiv(2), height - 2))
        gameBuilder.createAllies(_) >> [new Ally(new Position(width.intdiv(2) - 1, height - 2))]
        gameBuilder.createEnemies(_) >> [new Enemy(new Position(4, 2))]
        gameBuilder.createObstacles(_) >> [new Bush(new Position(5, 5)), new SmallStoneWall(new Position(7, 5)), new SmallWoodenWall(new Position(9, 5))]
        gameBuilder.createPowerUps(_) >> []

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        boolean res = gameModel.elementCanBePlaced(position)

        then:
        res == answer

        where:
        position                                      | answer
        new Position(arenaStartPoint - 1, 1)          | false
        new Position(arenaStartPoint, 1)              | true
        new Position(2, 2)                            | true
        new Position(arenaStartPoint, 0)              | true
        new Position(-1, -1)                          | false
        new Position(-1, 0)                           | false
        new Position(arenaStartPoint, -1)             | false
        new Position(width, height)                   | false
        new Position(width - 1, height - 1)           | true
        new Position(width, height - 1)               | false
        new Position(width - 1, height)               | false
        new Position(width.intdiv(2), height - 2)     | false
        new Position(width.intdiv(2) - 1, height - 2) | false
        new Position(4, 2)                            | false
        new Position(5, 5)                            | false
        new Position(7, 5)                            | false
        new Position(9, 5)                            | false
        new Position(5, 6)                            | true
    }

    def "test checkObstacles for finding obstacle in LoS and choosing the best through aimPenaltyCalculator"() {
        given:
        def target = new Position(5, 5)
        def gameBuilder = Mock(GameBuilder)
        //Scheme: (T-> target)
        //      BWS
        //      STW
        //      WSB
        gameBuilder.createObstacles(_) >> [new Bush(new Position(4, 4)), new Bush(new Position(6, 5)), new Bush(new Position(6, 6)),
                                           new SmallStoneWall(new Position(6, 4)), new SmallStoneWall(new Position(4, 5)), new SmallStoneWall(new Position(5, 6)),
                                           new SmallWoodenWall(new Position(5, 4)), new SmallWoodenWall(new Position(6, 5)), new SmallWoodenWall(new Position(4, 6))]

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        def penalty = gameModel.aimPenaltyCalculator(origin, target)

        then:
        penalty == expected

        where:
        origin             | expected
        new Position(3, 2) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(5, 2) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 20
        new Position(7, 2) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(7, 5) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 20
        new Position(7, 7) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(5, 7) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(4, 7) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(3, 5) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
    }

    def "test checkObstacles for finding obstacle in LoS or returning no obstacle and respective impact on aim"() {
        given:
        def target = new Position(5, 5)
        def gameBuilder = Mock(GameBuilder)
        //Scheme: (T-> target)
        //      B
        //       TW
        //       S
        gameBuilder.createObstacles(_) >> [new Bush(new Position(4, 4)),
                                           new SmallStoneWall(new Position(5, 6)),
                                           new SmallWoodenWall(new Position(6, 5))]

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        def penalty = gameModel.aimPenaltyCalculator(origin, target)

        then:
        penalty == expected

        where:
        origin             | expected
        new Position(3, 2) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 10
        new Position(5, 2) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5))))
        new Position(7, 2) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 20
        new Position(7, 5) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 20
        new Position(7, 7) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(5, 7) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(4, 7) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5)))) + 30
        new Position(3, 5) | 2 * toIntExact(round(Position.getDistance(origin, new Position(5, 5))))
    }

    def "test aimCalculator"() {
        given:
        def target = new Position(5, 5)
        def gameBuilder = Mock(GameBuilder)
        //Scheme: (T-> target)
        //      B
        //       TW
        //       S
        gameBuilder.createObstacles(_) >> [new Bush(new Position(4, 4)),
                                           new SmallStoneWall(new Position(5, 6)),
                                           new SmallWoodenWall(new Position(6, 5))]

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        int aim = gameModel.aimCalculator(new Player(position), target)

        then:
        aim == expected

        where:
        position                            | expected
        new Position(width - 1, height - 1) | 0             //Penalty > aim
        new Position(5, 3)                  | 66            //Penalty < aim
    }

    def "test damageCalculator (and its auxiliary damagePenaltyCalculator"() {
        given:
        def target = new Position(5, 5)
        def gameBuilder = Mock(GameBuilder)
        //Scheme: (T-> target)
        //      B
        //       TW
        //       S
        gameBuilder.createObstacles(_) >> [new Bush(new Position(4, 4)),
                                           new SmallStoneWall(new Position(5, 6)),
                                           new SmallWoodenWall(new Position(6, 5))]

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        int damage = gameModel.damageCalculator(new Player(position), target)

        then:
        damage == expected

        where:
        position                            | expected
        new Position(5, 7)                  | 0
        new Position(5, 3)                  | 10
        new Position(7, 5)                  | 5
        new Position(3, 3)                  | 10
    }

    def "test checkNewLevel"() {
        given:
        def gameBuilder = Mock(GameBuilder)
        gameBuilder.createEnemies(_) >> enemyList
        //Mainly irrelevant for the test but needed because of the private functions that run in case the check returns true
        gameBuilder.createObstacles(_) >> []
        gameBuilder.createPowerUps(_) >> []
        gameBuilder.createPlayer() >> new Player(new Position(5,5))
        gameBuilder.createAllies(_) >> [new Ally(new Position(6, 5))]

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        boolean res = gameModel.checkNewLevel()

        then:
        res == expected

        where:
        enemyList                                                       |   expected
        [new Enemy(new Position(1, 1))]                                 |   false
        [new Enemy(new Position(1, 1)), new Enemy(new Position(1, 2))]  |   false
        []                                                              |   true
    }

    def "test hitOrMiss bellow zero and bellow 100"(){
        given:
        def gameBuilder = Mock(GameBuilder)
        def random = Mock(Random)
        random.nextInt(*_) >> val
        gameBuilder.createPlayer() >> new Player(position)
        gameBuilder.createEnemies(_) >> [new Enemy(new Position(2, 1))]
        gameBuilder.createObstacles(_) >> []

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder, random)
        boolean res = gameModel.hitOrMiss(gameModel.getPlayer(), gameModel.getEnemyList().getFirst())

        then:
        res == expected

        where:
        position                            |   val     |   expected
        new Position(width-1, height-1)     |   0       |   false           //Aim is 0, so val is irrelevant
        new Position(2, 2)                  |   69      |   false           //Distance = 1, so, 70 - 1 * 2 (aimCalculator) = 68, so 68 returns true, 69 should return false
        new Position(2, 2)                  |   68      |   true
    }

    def "test hitOrMiss equal or over 100"(){
        given:
        def gameBuilder = Mock(GameBuilder)
        gameBuilder.createPlayer() >> new Player(position)
        gameBuilder.createEnemies(_) >> [new Enemy(new Position(2, 1))]
        gameBuilder.createObstacles(_) >> []

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        gameModel.getPlayer().increaseAim(bonus)
        //Aim = 70 + bonus - distance * 2
        boolean res = gameModel.hitOrMiss(gameModel.getPlayer(), gameModel.getEnemyList().getFirst())

        then:
        res == expected

        where:
        position                            |   bonus   |   expected
        new Position(2, 2)                  |   32      |   true
        new Position(2, 2)                  |   33      |   true
    }

    def "test applyPowerUps"(){
        given:
        def gameBuilder = Mock(GameBuilder)
        gameBuilder.createPlayer() >> new Player(position)
        gameBuilder.createPowerUps(_) >> [new ExtraHealth(new Position(2, 2)), new ExtraAim(new Position(3, 3))]

        when:
        GameModel gameModel = new GameModel(width, height, arenaStartPoint, gameBuilder)
        gameModel.applyPowerUps(gameModel.getPlayer())

        then:
        gameModel.getPowerUpList().size() == expected

        where:
        position                    |   x   |   expected
        new Position(1,1)           |   0   |   2
        new Position(2, 2)          |   1   |   1
        new Position(3, 3)          |   1   |   1
    }
}