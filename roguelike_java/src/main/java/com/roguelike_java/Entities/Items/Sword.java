package com.roguelike_java.Entities.Items;

import com.roguelike_java.UItext;

public class Sword extends Weapon{

    public Sword(int X, int Y){
        super("Epee", X, Y, "Sword.png", 20);
    }

    public void description(){
        UItext.printText("Une épée se trouve au sol.");
        UItext.printText(" ");
    }
    
}   
