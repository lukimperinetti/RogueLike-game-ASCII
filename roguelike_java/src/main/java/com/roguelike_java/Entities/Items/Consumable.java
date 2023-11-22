package com.roguelike_java.Entities.Items;


//Tout les items consommables
public abstract class Consumable extends Item{

    Consumable(String name, int X, int Y, String imageName, String tag, String shortName){
        super(name, X, Y, imageName, tag, shortName);

        listOption.add("Manger");
    }
    Consumable(String name, String imageName, String tag, String shortName){
        super(name, imageName, tag, shortName);

        listOption.add("Manger");
    }

}
