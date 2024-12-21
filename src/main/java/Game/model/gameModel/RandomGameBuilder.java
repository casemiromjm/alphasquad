package Game.model.gameModel;

import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Player;
import Game.model.elements.obstacles.Bush;
import Game.model.elements.obstacles.Obstacle;
import Game.model.elements.obstacles.SmallStoneWall;
import Game.model.elements.obstacles.SmallWoodenWall;
import Game.model.elements.powerUps.ExtraAim;
import Game.model.elements.powerUps.ExtraDamage;
import Game.model.elements.powerUps.ExtraHealth;
import Game.model.elements.powerUps.PowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGameBuilder extends GameBuilder {

    private final int width;
    private final int height;
    private final int arenaStartPoint;
    private final Random rand;


    public RandomGameBuilder(int width, int height, int arenaStartPoint, Random rand){
        this.width = width;
        this.height = height;
        this.arenaStartPoint = arenaStartPoint;
        this.rand = rand;
    }

    private Position generatePosition(int xOrigin, int xLimit, int yOrigin, int yLimit){
        int x = rand.nextInt(xOrigin, xLimit);
        int y = rand.nextInt(yOrigin, yLimit);

        return new Position(x, y);
    }

    @Override
    protected Player createPlayer(){
        return new Player(new Position(width/2, height - 2));
    }

    @Override
    protected List<Ally> createAllies(GameModel gameModel){
        //Elements maintained from beginning to end
        List<Ally> allyList = new ArrayList<>();

        while(allyList.size() < 2){
            int x = rand.nextInt(width/2 - 3, width/2 + 4);
            int y = height - 2;

            if(gameModel.elementCanBePlaced(new Position(x, y)))
                allyList.add(new Ally(new Position(x, y)));

        }

        return allyList;
    }

    @Override
    protected List<Enemy> createEnemies(GameModel gameModel){
        List<Enemy> enemyList = new ArrayList<>();
        while(enemyList.size() < gameModel.getLevel() * 3){
            Position position = generatePosition(arenaStartPoint, width, 0, height/4);

            if(gameModel.elementCanBePlaced(position))
                enemyList.add(new Enemy(position));

        }
        return enemyList;
    }

    @Override
    protected List<Obstacle> createObstacles(GameModel gameModel) {
        List<Obstacle> obstacleList = new ArrayList<>();
        //Add stone walls
        for (int i = 0; i < 2; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);

            if (gameModel.elementCanBePlaced(position)) {
                obstacleList.add(new SmallStoneWall(position));
                i++;
            }
        }

        for (int i = 0; i < 4; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);

            if (gameModel.elementCanBePlaced(position)) {
                obstacleList.add(new SmallWoodenWall(position));
                i++;
            }
        }

        for (int i = 0; i < 4; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);

            if (gameModel.elementCanBePlaced(position)) {
                obstacleList.add(new Bush(position));
                i++;
            }
        }

        return obstacleList;

    }

    @Override
    protected List<PowerUp> createPowerUps(GameModel gameModel){
        List<PowerUp> powerUpList = new ArrayList<>();
        //To improve
        //Places an ExtraHealth Power-Up (PU)
        List<Position> powerup_pos = new ArrayList<>();
        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(gameModel.elementCanBePlaced(position)){
                powerUpList.add(new ExtraHealth(position));
                powerup_pos.add(position);
                break;
            }
        }

        //Places an ExtraAim (PU)
        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(gameModel.elementCanBePlaced(position) && !powerup_pos.contains(position)){
                powerUpList.add(new ExtraAim(position));
                break;
            }
        }

        //Places an ExtraDamage (PU)
        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(gameModel.elementCanBePlaced(position) && !powerup_pos.contains(position)){
                powerUpList.add(new ExtraDamage(position));
                break;
            }
        }
        return powerUpList;
    }
}
