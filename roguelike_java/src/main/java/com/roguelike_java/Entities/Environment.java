package com.roguelike_java.Entities;

//Classe qui regroupe tout les objets de décors (murs, sols, table, etc);
public class Environment extends Entity {

    public Environment(String name, int X, int Y, String imageName, String tag){
        super(name, X, Y, imageName, tag);
    }
    
}
