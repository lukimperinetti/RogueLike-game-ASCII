package com.roguelike_java.Entities.Items;


//Classe pour les munitions a utiliser pour arme a distance.
public abstract class Ammunition extends Item{

    Ammunition(String name, int X, int Y, String imageName, String tag, String shortName){
        super(name, X, Y, imageName, tag, shortName);
    }
    Ammunition(String name, String imageName, String tag, String shortName){
        super(name, name, tag, shortName);
    }

    
}
