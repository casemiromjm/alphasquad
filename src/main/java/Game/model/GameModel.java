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
import Game.model.elements.powerUps.ExtraAim;
import Game.model.elements.powerUps.ExtraDamage;
import Game.model.elements.powerUps.ExtraHealth;
import Game.model.elements.powerUps.PowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public class GameModel extends Model {
    private final List<Enemy> enemyList = new ArrayList<>();
    private final List<Ally> allyList = new ArrayList<>();
    private Player player;
    private final List<Obstacle> obstacleList = new ArrayList<>();
    private final List<PowerUp> powerUpList = new ArrayList<>();
    private int level = 1;
    private final int width;
    private final int height;
    private int arenaStartPoint;
    private final Random rand;

    public GameModel(int width, int height, int arenaStartPoint) {
        this.width = width;
        this.height = height;
        this.arenaStartPoint = arenaStartPoint;
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

    public int getArenaStartPoint() {
        return arenaStartPoint;
    }

    public void setArenaStartPoint(int arenaStartPoint) {
        this.arenaStartPoint = arenaStartPoint;
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

    public void nextLevel(){
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
        //Elements maintained from beginning to end
        player = new Player(new Position(width/2, height - 2));

        while(allyList.size() < 2){
            int x = rand.nextInt(width/2 - 3, width/2 + 4);
            int y = height - 2;

            if(elementCanBePlaced(new Position(x, y)))
                allyList.add(new Ally(new Position(x, y)));

        }
    }

    private void createLevelElements(){
        //Creating Level specific elements
        while(enemyList.size() < level * 3){
            Position position = generatePosition(arenaStartPoint, width, 0, height/4);

            if(elementCanBePlaced(position))
                enemyList.add(new Enemy(position));

        }

        //Add stone walls
        for(int i = 0; i < 2; i++) {
            Position position = generatePosition(arenaStartPoint, width, height/5, 4 * height/5);

            if (elementCanBePlaced(position))
                obstacleList.add(new SmallStoneWall(position));

        }

        for(int i = 0; i < 4; i++){
            Position position = generatePosition(arenaStartPoint, width, height/5, 4 * height/5);

            if (elementCanBePlaced(position))
                obstacleList.add(new SmallWoodenWall(position));

        }

        for(int i = 0; i < 4; i++){
            Position position = generatePosition(arenaStartPoint, width, height/5, 4 * height/5);

            if (elementCanBePlaced(position))
                obstacleList.add(new Bush(position));

        }

        //To improve
        List<Position> powerup_pos = new ArrayList<>();
        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(elementCanBePlaced(position)){
                powerUpList.add(new ExtraHealth(position));
                powerup_pos.add(position);
                break;
            }
        }

        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(elementCanBePlaced(position) && !powerup_pos.contains(position)){
                powerUpList.add(new ExtraAim(position));
                break;
            }
        }

        while(true){
            Position position = generatePosition(arenaStartPoint, width, 4 * height/5, 4 * height);

            if(elementCanBePlaced(position) && !powerup_pos.contains(position)){
                powerUpList.add(new ExtraDamage(position));
                break;
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
        if(position.getX() < arenaStartPoint || position.getX() > width - 1 || position.getY() < 0 || position.getY() > height - 1)
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
            return 2 * toIntExact(round(getDistance(origin, target))) + obstacle.getProtection();

        return 2 * toIntExact(round(getDistance(origin, target)));
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
            //Hard to understand, to improve
            if(getDistance(ob_position, target) < 2){
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

    public double getDistance(Position p1, Position p2){
        return sqrt(pow(p1.getX() - p2.getX(), 2) + pow(p1.getY() - p2.getY(), 2));
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
            int x = rand.nextInt(width/2 - 3, width/2 + 4);
            int y = height - 2;
            while(true) {
                if (elementCanBePlaced(new Position(x, y))) {
                    ally.setPosition(new Position(x, y));
                    improveFighter(ally);
                    break;
                }
            }
        }
    }

    private void improveFighter(Fighter fighter){
        fighter.setHitPoints(fighter.getHitPoints() + 20);
        fighter.setAim(fighter.getAim() + 10);
        fighter.setDamage(fighter.getDamage() + 5);
    }
}
