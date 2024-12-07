package Game.model;

import Game.model.elements.Element;
import Game.model.elements.Position;
import Game.model.elements.fighter.Ally;
import Game.model.elements.fighter.Enemy;
import Game.model.elements.fighter.Player;
import Game.model.elements.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel extends Model {
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Ally> allyList = new ArrayList<>();
    private Player player;
    private List<Obstacle> obstacleList;
    private int difficulty = 1;
    private int width;
    private int height;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        createInitialElements();

        //create the first Level
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

    public void increaseDifficulty(){
        difficulty++;
    }

    private void createInitialElements(){
        //Elements maintained from beginning to end
        player = new Player(new Position(width/2, height - 2));

        while(allyList.size() < 2){
            Random rand = new Random();
            int x = rand.nextInt(11, width);
            int y = height - 2;

            if(elementCanBePlaced(new Position(x, y))){
                allyList.add(new Ally(new Position(x, y)));
            }
        }
    }

    public void createLevelElements(){
        //Creating Level specific elements

        while(enemyList.size() < difficulty * 3){
            Random rand = new Random();
            int x = rand.nextInt(11, width);
            int y = rand.nextInt(height/4);

            if(elementCanBePlaced(new Position(x, y))){
                enemyList.add(new Enemy(new Position(x, y)));
            }
        }
    }

    public boolean elementCanBePlaced(Position position){
        //To make more efficient
        if(player != null && player.getPosition().equals(position)){
            return false;
        }

        for(Element el : enemyList){
            if(el.getPosition().equals(position)){
                return false;
            }
        }

        for(Element el : allyList){
            if(el.getPosition().equals(position)){
                return false;
            }
        }

        for(Element el : enemyList){
            if(el.getPosition().equals(position)){
                return false;
            }
        }

        return true;
    }
}
