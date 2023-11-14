package com.roguelike_java.Entities.Items;

public abstract class Weapon extends Item{
    
    protected int atk;

    Weapon(String name, int X, int Y, String imageName, int atk){
        super(name, X, Y, imageName, "weapon");

        this.atk = atk;
    }

    public int getAtk(){
        return atk;
    }

}
