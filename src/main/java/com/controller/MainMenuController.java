package com.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.model.MainMenuModel;
import com.view.MainMenuView;

import java.io.IOException;

public class MainMenuController {

    private Screen screen;

    public MainMenuController(Screen screen) {
        this.screen = screen;
    }

    public void run() throws IOException {
        MainMenuModel mainMenuModel = new MainMenuModel();
        MainMenuView mainMenuView = new MainMenuView(mainMenuModel, screen);

        while (true) {
            screen.clear();
            mainMenuView.draw();
            KeyStroke key = screen.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                break;

            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'h') {
                screen.clear();
                screen.refresh();
                HelpScreen helpScreen = new HelpScreen(screen);
                helpScreen.run();

            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
                screen.clear();
                screen.refresh();
                Game game = new Game(screen);
                game.run();

            } else if (key.getKeyType() == KeyType.EOF) {
                break;
            }
        }
    }
}
