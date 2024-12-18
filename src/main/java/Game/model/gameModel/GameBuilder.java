package Game.model.gameModel;

import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
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

public class GameBuilder {

    int width;
    int height;
    int level;
    int arenaStartPoint;
    Random rand;
    GameUtils gameUtils;

    protected GameBuilder(int width, int height, int arenaStartPoint, int level, Random rand, GameUtils gameUtils){
        this.width = width;
        this.height = height;
        this.arenaStartPoint = arenaStartPoint;
        this.level = level;
        this.rand = rand;
        this.gameUtils = gameUtils;
    }

    private Position generatePosition(int xOrigin, int xLimit, int yOrigin, int yLimit){
        int x = rand.nextInt(xOrigin, xLimit);
        int y = rand.nextInt(yOrigin, yLimit);

        return new Position(x, y);
    }

    protected Player createPlayer(){
        return new Player(new Position(width/2, height - 2));
    }

    protected List<Ally> createAllies(){
        //Elements maintained from beginning to end
        List<Ally> allyList = new ArrayList<>();

        while(allyList.size() < 2){
            int x = rand.nextInt(width/2 - 3, width/2 + 4);
            int y = height - 2;

            if(gameUtils.elementCanBePlaced(new Position(x, y)))
                allyList.add(new Ally(new Position(x, y)));

        }

        return allyList;
    }

    protected List<Enemy> createEnemies(){
        List<Enemy> enemyList = new ArrayList<>();
        while(enemyList.size() < level * 3){
            Position position = generatePosition(arenaStartPoint, width, 0, height/4);

            if(gameUtils.elementCanBePlaced(position))
                enemyList.add(new Enemy(position));

        }
        return enemyList;
    }

    protected List<Obstacle> createObstacles() {
        List<Obstacle> obstacleList = new ArrayList<>();
        //Add stone walls
        for (int i = 0; i < 2; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);

            if (gameUtils.elementCanBePlaced(position)) {
                obstacleList.add(new SmallStoneWall(position));
                i++;
            }
        }

        for (int i = 0; i < 4; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);

            if (gameUtils.elementCanBePlaced(position)) {
                obstacleList.add(new SmallWoodenWall(position));
                i++;
            }
        }

        for (int i = 0; i < 4; ) {
            Position position = generatePosition(arenaStartPoint, width, height / 5, 4 * height / 5);

            if (gameUtils.elementCanBePlaced(position)) {
                obstacleList.add(new Bush(position));
                i++;
            }
        }

        return obstacleList;

    }

    protected List<PowerUp> createPowerUps(){
        List<PowerUp> powerUpList = new ArrayList<>();
        //To improve
        //Places an ExtraHealth Power-Up (PU)
        List<Position> powerup_pos = new ArrayList<>();
        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(gameUtils.elementCanBePlaced(position)){
                powerUpList.add(new ExtraHealth(position));
                powerup_pos.add(position);
                break;
            }
        }

        //Places an ExtraAim (PU)
        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(gameUtils.elementCanBePlaced(position) && !powerup_pos.contains(position)){
                powerUpList.add(new ExtraAim(position));
                break;
            }
        }

        //Places an ExtraDamage (PU)
        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(gameUtils.elementCanBePlaced(position) && !powerup_pos.contains(position)){
                powerUpList.add(new ExtraDamage(position));
                break;
            }
        }
        return powerUpList;
    }
}
