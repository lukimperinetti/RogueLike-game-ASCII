package com.roguelike_java.Entities.Items;

import com.roguelike_java.UI.UItext;

public class Apple extends Consumable {
    
    Apple(int X, int Y){
        super("Pomme", X, Y, "pomme.png", "nourriture", "pomme");
    }
    Apple(){
        super("Pomme", "pomme.png", "nourriture", "pomme");
    }

    public void description(){
        UItext.printText("Une pomme se trouve au sol.");
        UItext.printText(" ");
    }
}
