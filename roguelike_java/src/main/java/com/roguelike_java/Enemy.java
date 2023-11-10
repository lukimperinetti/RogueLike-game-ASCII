package com.roguelike_java;

public class Enemy extends Unit{
    
    Enemy(String name, int X, int Y, int hp){
        super(name, X, Y, "Hashtag.png", hp);
    }

    public void doAction(){
        //FAIT JOUER L'ENNEMI.
        //A OVERRIDE POUR FAIRE UNE VRAI IA
    }
}
