package com.view.elements.characters;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.model.elements.characters.Player;
import com.view.elements.Drawable;

public class PlayerDraw implements Drawable {
    Player player;
    TextGraphics textGraphics;

    public PlayerDraw(Player player, TextGraphics textGraphics) {
        this.player = player;
        this.textGraphics = textGraphics;
    }

    public void draw(){
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), "P");
    }
}
