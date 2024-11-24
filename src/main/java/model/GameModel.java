package model;

import model.elements.obstacles.Obstacle;

import java.util.List;

public class GameModel {
    private int difficulty;
    //Allies, player and enemies since they share the similar functionalities (to change later?)
    private List<Character> characterList;
    private List<Obstacle> obstacleList;

    public GameModel(int difficulty) {
        this.difficulty = difficulty;
    }

    void createElements(){
    }
}
