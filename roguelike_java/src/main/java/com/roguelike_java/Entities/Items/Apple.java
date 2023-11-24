package com.roguelike_java.Entities.Items;

import com.roguelike_java.Inventory;
import com.roguelike_java.ListEntity;
import com.roguelike_java.UI.UItext;

public class Apple extends Consumable {
    
    public Apple(int X, int Y){
        super("Pomme", X, Y, "apple.png", "nourriture", "Pomme");
    }
    public Apple(){
        super("Pomme", "pomme.png", "nourriture", "Pomme");
    }

    public void description(){
        UItext.printText("Une pomme se trouve au sol.");
        UItext.printText(" ");
    }

    @Override
    public void itemAction(String str){

        super.itemAction(str);

        if (str.equals("Manger")){
            Inventory.removeItem(this);
            
            UItext.printText("Boris mange une pomme.");
            ListEntity.getBoris().heal(5);
        }
    }
}
