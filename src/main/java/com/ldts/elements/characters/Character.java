package com.ldts.elements.characters;

import com.ldts.elements.Elements;

//The characters of the game, both enemies, friendlies and the player
public abstract class Character extends Elements {
    int hitPoints;
    int level;
    int aim;
    int experience;

    public Character(){
        level = 1;
        hitPoints = 20*level;
        aim = 70;       //Subject to change
        experience = 0;
    }

    public Character(int level){
        this.level = level;
        hitPoints = 20*level;
        aim = 70;       //Subject to change
        experience = 0;
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
}
