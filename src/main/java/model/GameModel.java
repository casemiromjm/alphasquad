package model;

import model.elements.Element;
import model.elements.Position;
import model.elements.characters.Ally;
import model.elements.characters.Enemy;
import model.elements.characters.Player;
import model.elements.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    private int difficulty;
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Ally> allyList = new ArrayList<>();
    private Player player;
    private List<Obstacle> obstacleList;
    private int width;
    private int height;

    public GameModel(int difficulty, int width, int height) {
        this.difficulty = difficulty;
        this.width = width;
        this.height = height;
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

    public void createInitialElements(){
        //Values are temporary
        player = new Player(new Position(width/2, 1), 1);

        while(enemyList.size() < difficulty * 3){
            Random rand = new Random();
            int x = rand.nextInt(width);
            int y = rand.nextInt(height/2);

            if(elementCanBePlaced(new Position(x, y))){
                enemyList.add(new Enemy(new Position(x, y), 1));
            }
        }

        while(allyList.size() < 3){
            Random rand = new Random();
            int x = rand.nextInt(width);
            int y = rand.nextInt(2);

            if(elementCanBePlaced(new Position(x, y))){
                allyList.add(new Ally(new Position(x, y), 1));
            }
        }
    }

    boolean elementCanBePlaced(Position position){
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
