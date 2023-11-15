package com.roguelike_java.Entities.Items;

import com.roguelike_java.App;
import com.roguelike_java.Grid;
import com.roguelike_java.Entities.Entity;

public abstract class Item extends Entity{
    
    public Item(String name, int X, int Y, String imageName, String tag){
        super(name, X, Y, imageName, tag);

        Grid.setEntity(this);
        App.displaySprite(this);

        visibility = true;
    }

    public abstract void description();
}
