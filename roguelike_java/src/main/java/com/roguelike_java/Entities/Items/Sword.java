package com.roguelike_java.Entities.Items;

import com.roguelike_java.UI.UItext;

public class Sword extends Weapon{

    public Sword(int X, int Y){
        super("Epee", X, Y, "Sword.png", 20);
    }
    public Sword(){
        super("Epee", "Sword.png", 20);
    }

    public void description(){
        UItext.printText("Une epee se trouve au sol.");
        UItext.printText(" ");
    }
    
}   
