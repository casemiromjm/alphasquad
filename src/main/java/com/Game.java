package com;

import com.googlecode.lanterna.screen.Screen;
import com.controller.GameController;
import com.model.GameModel;
import com.view.GameView;

public class Game {
    private GameController gameController;
    private GameView gameView;
    private GameModel gameModel;
    private Screen screen;

    public Game(Screen screen){
        this.screen = screen;
        gameController = new GameController();
        gameView = new GameView();
        gameModel = new GameModel();
    }

    public void run(){

    }
}
