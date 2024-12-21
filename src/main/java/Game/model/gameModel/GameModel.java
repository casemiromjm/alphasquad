package Game.model.gameModel;

import Game.model.Model;
import Game.model.elements.Element;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Fighter;
import Game.model.elements.fighter.Player;
import Game.model.elements.obstacles.Obstacle;
import Game.model.elements.powerUps.PowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class GameModel extends Model {
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Ally> allyList = new ArrayList<>();
    private Player player;
    private List<Obstacle> obstacleList = new ArrayList<>();
    private List<PowerUp> powerUpList = new ArrayList<>();
    private int level = 1;
    private final int width;
    private final int height;
    private final int arenaStartPoint;
    private final Random rand;
    private final GameBuilder gameBuilder;

    public GameModel(int width, int height, int arenaStartPoint, GameBuilder gameBuilder) {
        this.width = width;
        this.height = height;
        this.arenaStartPoint = arenaStartPoint;
        this.gameBuilder = gameBuilder;
        rand = new Random();
        createInitialElements();

        //create the first Level
        createLevelElements();
    }

    //Mainly for testing purposes
    public GameModel(int width, int height, int arenaStartPoint, GameBuilder gameBuilder, Random rand) {
        this.width = width;
        this.height = height;
        this.arenaStartPoint = arenaStartPoint;
        this.gameBuilder = gameBuilder;
        this.rand = rand;
        createInitialElements();
        createLevelElements();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArenaStartPoint() {
        return arenaStartPoint;
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

    public List<PowerUp> getPowerUpList() {
        return powerUpList;
    }

    public int getLevel(){
        return level;

    }


    public boolean elementCanBePlaced(Position position){
        if(position.getX() < arenaStartPoint
                || position.getX() > width - 1
                || position.getY() < 0
                || position.getY() > height - 1)
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
            return 2 * toIntExact(round(Position.getDistance(origin, target))) + obstacle.getProtection();

        return 2 * toIntExact(round(Position.getDistance(origin, target)));
    }

    public int aimCalculator(Fighter origin, Position target){
        int aim = origin.getAim() - aimPenaltyCalculator(origin.getPosition(), target);
        return Math.max(aim, 0);

    }

    public int damagePenaltyCalculator(Position origin, Position target){
        Obstacle obstacle = checkObstacles(origin, target);

        if(obstacle != null)
            return obstacle.getDamageReduction();

        return 0;
    }

    public int damageCalculator(Fighter origin, Position target){
        int damage = origin.getDamage() - damagePenaltyCalculator(origin.getPosition(), target);
        return Math.max(damage, 0);

    }

    //Checks for any obstacle adjacent to the target position that may interfere with the Line of Sight (LoS) from the origin
    private Obstacle checkObstacles(Position origin, Position target){
        //In case there is more than one possible obstacle, choose the one with the biggest protection
        List<Obstacle> candidates = new ArrayList<>();
        Obstacle result = null;

        for(Obstacle ob : obstacleList){
            Position ob_position = ob.getPosition();
            //Check for adjacent obstacles that are between two positions
            if(Position.getDistance(ob_position, target) < 2){
                if((ob_position.getX() >= origin.getX() && ob_position.getY() >= origin.getY() && ob_position.getX() <= target.getX() && ob_position.getY() <= target.getY()) ||
                        (ob_position.getX() <= origin.getX() && ob_position.getY() >= origin.getY() && ob_position.getX() >= target.getX() && ob_position.getY() <= target.getY()) ||
                        (ob_position.getX() <= origin.getX() && ob_position.getY() <= origin.getY() && ob_position.getX() >= target.getX() && ob_position.getY() >= target.getY()) ||
                        (ob_position.getX() >= origin.getX() && ob_position.getY() <= origin.getY() && ob_position.getX() <= target.getX() && ob_position.getY() >= target.getY())){

                    candidates.add(ob);
                }
            }
        }

        for(Obstacle ob : candidates){
            if(result == null){
                result = ob;
            }

            else if(ob.getProtection() > result.getProtection()){
                result = ob;
            }
        }

        return result;
    }

    private void nextLevel(){
        level++;
        obstacleList.clear();
        enemyList.clear();
        powerUpList.clear();
        newLevelPermElements();

        createLevelElements();
    }

    public boolean checkNewLevel(){
        if(enemyList.isEmpty()){
            nextLevel();
            return true;
        }
        return false;
    }

    private void createInitialElements(){
        player = gameBuilder.createPlayer();
        allyList = gameBuilder.createAllies(this);
    }

    private void createLevelElements(){
        enemyList = gameBuilder.createEnemies(this);
        obstacleList = gameBuilder.createObstacles(this);
        powerUpList = gameBuilder.createPowerUps(this);
    }

    public boolean hitOrMiss(Fighter origin, Fighter target){
        int totalAim = aimCalculator(origin, target.getPosition());

        if(totalAim <= 0)
            return false;

        else if(totalAim >= 100)
            return true;

        int hitValue = rand.nextInt(1, 101);

        return hitValue <= totalAim;
    }

    public void cleanDeath(){
        enemyList.removeIf(Fighter::isDead);
        allyList.removeIf(Fighter::isDead);
    }

    public void applyPowerUps(Fighter fighter){
        for(int i = 0; i < powerUpList.size(); i++) {
            if (powerUpList.get(i).getPosition().equals(fighter.getPosition())){
                powerUpList.get(i).apply(fighter);
                powerUpList.remove(i);
                break;
            }
        }
    }

    private void newLevelPermElements(){
        player.setPosition(new Position(width/2, height - 2));
        improveFighter(player);

        for(Ally ally : allyList){
            while(true) {
                int x = rand.nextInt(width/2 - 3, width/2 + 4);
                int y = height - 2;
                if (elementCanBePlaced(new Position(x, y))) {
                    ally.setPosition(new Position(x, y));
                    improveFighter(ally);
                    break;
                }
            }
        }
    }

    private void improveFighter(Fighter fighter){
        fighter.increaseHealth(20);
        fighter.increaseAim(10);
        fighter.increaseDamage(5);
    }

}
