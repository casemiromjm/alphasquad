package Game.model;

import Game.model.elements.Element;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.fighter.Player;
import Game.model.elements.obstacles.Bush;
import Game.model.elements.obstacles.Obstacle;
import Game.model.elements.obstacles.SmallStoneWall;
import Game.model.elements.obstacles.SmallWoodenWall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class GameModel extends Model {
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Ally> allyList = new ArrayList<>();
    private Player player;
    private List<Obstacle> obstacleList = new ArrayList<>();
    private int difficulty = 1;
    private int width;
    private int height;
    private Random rand;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        rand = new Random();
        createInitialElements();

        //create the first Level
        createLevelElements();
    }

    public GameModel(int width, int height, Random rand) {
        this.width = width;
        this.height = height;
        this.rand = rand;
        createInitialElements();
        createLevelElements();
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public List<Ally> getAllyList() {
        return allyList;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public void nextLevel(){
        difficulty++;
        obstacleList.clear();
        enemyList.clear();
        //powerUpList.clear();

        createLevelElements();
    }

    private void createInitialElements(){
        //Elements maintained from beginning to end
        player = new Player(new Position(width/2, height - 2));

        while(allyList.size() < 2){
            int x = rand.nextInt(12, width);
            int y = height - 2;

            if(elementCanBePlaced(new Position(x, y))){
                allyList.add(new Ally(new Position(x, y)));
            }
        }
    }

    private void createLevelElements(){
        //Creating Level specific elements
        while(enemyList.size() < difficulty * 3){
            Position position = generatePosition(12, width, 0, height/4);

            if(elementCanBePlaced(position)){
                enemyList.add(new Enemy(position));
            }
        }

        //Add stone walls
        for(int i = 0; i < 2; i++) {
            Position position = generatePosition(12, width, height/4, 3 * height/4);

            if (elementCanBePlaced(position)) {
                obstacleList.add(new SmallStoneWall(position));
            }
        }

        for(int i = 0; i < 4; i++){
            Position position = generatePosition(12, width, height/4, 3 * height/4);

            if (elementCanBePlaced(position)) {
                obstacleList.add(new SmallWoodenWall(position));
            }
        }

        for(int i = 0; i < 4; i++){
            Position position = generatePosition(12, width, height/4, 3 * height/4);

            if (elementCanBePlaced(position)) {
                obstacleList.add(new Bush(position));
            }
        }
    }

    private Position generatePosition(int xOrigin, int xLimit, int yOrigin, int yLimit){
        int x = rand.nextInt(xOrigin, xLimit);
        int y = rand.nextInt(yOrigin, yLimit);

        return new Position(x, y);
    }

    public boolean elementCanBePlaced(Position position){
        //To make more efficient
        if(position.getX() < 12 || position.getX() > width - 1 || position.getY() < 0 || position.getY() > height - 1)
            return false;

        if(player.getPosition().equals(position))
            return false;


        for(Element el : enemyList){
            if(el.getPosition().equals(position))
                return false;

        }

        for(Element el : allyList){
            if(el.getPosition().equals(position))
                return false;

        }

        for(Element el : enemyList){
            if(el.getPosition().equals(position))
                return false;

        }

        for (Element el : obstacleList){
            if(el.getPosition().equals(position))
                return false;

        }

        return true;
    }

    //Calculates the aim penalty from one position to another
    public int aimPenaltyCalculator(Position origin, Position target){
        Obstacle obstacle = checkObstacles(origin, target);

        if(obstacle != null)
            return toIntExact(round(sqrt(pow(origin.getX() - target.getX(), 2) + pow(origin.getY() - target.getY(), 2))) + obstacle.getProtection());

        return toIntExact(round(sqrt(pow(origin.getX() - target.getX(), 2) + pow(origin.getY() - target.getY(), 2))));
    }

    public int damagePenaltyCalculator(Position origin, Position target){
        Obstacle obstacle = checkObstacles(origin, target);

        if(obstacle != null)
            return obstacle.getDamageReduction();

        return 0;
    }


    //Checks for any obstacle adjacent to the target position that may interfere with the Line of Sight (LoS) from the origin
    private Obstacle checkObstacles(Position origin, Position target){
        //In case there is more than one possible obstacle, choose the one with the biggest protection
        List<Obstacle> candidates;
        Obstacle result;

        for(Obstacle ob : obstacleList){
            if(ob.getPosition().getX() )
        }
        return result;
    }
}
