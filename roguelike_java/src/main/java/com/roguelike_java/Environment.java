package com.roguelike_java;


//Classe qui regroupe tout les objets de décors (murs, sols, table, etc);
public class Environment extends Entity {

    Environment(String name, int X, int Y, String imageName, String tag){
        super(name, X, Y, imageName, tag);
    }
    
}
