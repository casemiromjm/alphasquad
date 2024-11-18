package com.ldts.elements.characters;

import com.googlecode.lanterna.TextColor;
import com.ldts.elements.Elements;
import com.ldts.elements.Position;

//The characters of the game, both enemies, friendlies and the player
public abstract class Character extends Elements {
    String name;
    boolean isDead;
    int hitPoints;
    int level;
    int aim;
    int experience;

    public Character(String symbol, TextColor color, Position position, String name){
        super(symbol, color, position);
        this.name = name;
        isDead = false;
        level = 1;
        hitPoints = 20*level;
        aim = 70;       //Subject to change
        experience = 0;
    }


    public Character(String symbol, TextColor color, Position position, String name, int level){
        super(symbol, color, position);
        this.name = name;
        isDead = false;
        this.level = level;
        hitPoints = 20*level;
        aim = 70;       //Subject to change
        experience = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAim() {
        return aim;
    }

    public void setAim(int aim) {
        this.aim = aim;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void increaseLevel(){
        level++;
        hitPoints += 20;
        aim += 10;
    }

    public abstract void move();

    public abstract void selectTarget();

    public abstract void fire();
}
