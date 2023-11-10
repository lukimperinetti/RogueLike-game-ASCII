package com.roguelike_java;

//Classe de base de tt les entités présentes in game
public class Entity {
    
    private String name;
    private int ID;
    private static int globalID;

    Entity(String name){
        
        //ID :
        this.ID = globalID;
        globalID++;

        this.name = name;

    }
}
