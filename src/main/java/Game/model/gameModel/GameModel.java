package Game.model.gameModel;

import Game.model.Model;
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
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Ally> allyList = new ArrayList<>();
    private Player player;
    private List<Obstacle> obstacleList = new ArrayList<>();
    private List<PowerUp> powerUpList = new ArrayList<>();
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

    //Mainly for testing purposes
    public GameModel(int width, int height, int arenaStartPoint, Random rand) {
        this.width = width;
        this.height = height;
        this.arenaStartPoint = arenaStartPoint;
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
        GameUtils gameUtils = new GameUtils(this);
        GameBuilder gameBuilder = new GameBuilder(width, height, arenaStartPoint, level, rand, gameUtils);
        player = gameBuilder.createPlayer();
        allyList = gameBuilder.createAllies();
    }

    private void createLevelElements(){
        GameUtils gameUtils = new GameUtils(this);
        GameBuilder gameBuilder = new GameBuilder(width, height, arenaStartPoint, level, rand, gameUtils);
        enemyList = gameBuilder.createEnemies();
        obstacleList = gameBuilder.createObstacles();
        powerUpList = gameBuilder.createPowerUps();
    }

    public boolean hitOrMiss(Fighter origin, Fighter target){
        GameUtils gameUtils = new GameUtils(this);
        int totalAim = gameUtils.aimCalculator(origin, target.getPosition());

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

    //Refactor
    private void newLevelPermElements(){
        GameUtils gameUtils = new GameUtils(this);
        player.setPosition(new Position(width/2, height - 2));
        improveFighter(player);

        for(Ally ally : allyList){
            while(true) {
                int x = rand.nextInt(width/2 - 3, width/2 + 4);
                int y = height - 2;
                if (gameUtils.elementCanBePlaced(new Position(x, y))) {
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
