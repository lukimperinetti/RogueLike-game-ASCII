package com.roguelike_java.Entities.Items;

import com.roguelike_java.App;
import com.roguelike_java.Entities.Entity;

public class Item extends Entity{
    
    public Item(String name, int X, int Y, String imageName, String tag){
        super(name, X, Y, "Sword.png", tag);
        App.displaySprite(this);
    }
}
