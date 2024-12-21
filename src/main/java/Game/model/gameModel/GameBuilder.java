package Game.model.gameModel;

import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Player;
import Game.model.elements.obstacles.Obstacle;
import Game.model.elements.powerUps.PowerUp;

import java.util.List;

public abstract class GameBuilder {
    protected abstract Player createPlayer();

    protected abstract List<Ally> createAllies(GameModel gameModel);

    protected abstract List<Enemy> createEnemies(GameModel gameModel);

    protected abstract List<Obstacle> createObstacles(GameModel gameModel);

    protected abstract List<PowerUp> createPowerUps(GameModel gameModel);
}
