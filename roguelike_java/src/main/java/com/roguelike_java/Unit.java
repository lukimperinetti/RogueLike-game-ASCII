package com.roguelike_java;

//Class pour les unites (ennemi comme PJ)

public class Unit extends Entity {

    protected int hp;
    protected int atk;

    Unit(String name, int X, int Y, String imageName, int hp){
        super(name, X, Y, imageName);

        this.hp = hp;
        this.atk = 1; //atk par defaut.
    }
    
}
