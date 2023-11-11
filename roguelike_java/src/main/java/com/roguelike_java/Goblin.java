package com.roguelike_java;

public class Goblin extends Enemy{

    Goblin(int X, int Y){
        super("Goblin", X, Y, 50, 10);
        App.displaySprite(this);
    }

    public void doAction(){
        relativeMove(1, 0);
    }
}
