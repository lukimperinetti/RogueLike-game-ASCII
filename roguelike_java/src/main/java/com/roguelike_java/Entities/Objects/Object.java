package com.roguelike_java.Entities.Objects;

import com.roguelike_java.Entities.Entity;

//Classe générale d'objet dont tout les autres hériterons.
public class Object extends Entity{

    Object(String name, int X, int Y, String imageName, String tag){
        super(name, X, Y, "Sword.png", tag);
    }
}
