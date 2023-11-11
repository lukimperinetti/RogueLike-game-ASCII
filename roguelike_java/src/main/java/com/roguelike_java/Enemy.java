package com.roguelike_java;

public abstract class Enemy extends Unit{
    
    Enemy(String name, int X, int Y, int hp, int atk){
        super(name, X, Y, "Hashtag.png", hp, "enemy");
        ListEntity.addEnemy(this); //S'ajoute a la liste d'ennemis a jouer.
    }

    public abstract void doAction();
}
