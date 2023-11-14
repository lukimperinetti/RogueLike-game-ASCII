package com.roguelike_java.Entities.Items;

import com.roguelike_java.UItext;

public class Sword extends Item{

    public Sword(int X, int Y){
        super("Sword", X, Y, "Sword.png", "Weapon");
    }

    public void description(){
        UItext.printText("Une épée se trouve au sol.");
        UItext.printText(" ");
    }
    
}
