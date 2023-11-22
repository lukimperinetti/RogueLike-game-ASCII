package com.roguelike_java.Entities.Items;

import com.roguelike_java.App;
import com.roguelike_java.Grid;
import com.roguelike_java.Inventory;
import com.roguelike_java.ListEntity;
import com.roguelike_java.Entities.Entity;

public abstract class Item extends Entity{
    
    boolean isInInventory;

    public Item(String name, int X, int Y, String imageName, String tag){
        super(name, X, Y, imageName, tag);

        Grid.setEntity(this);
        App.displaySprite(this);

        visibility = true;

        isInInventory = false;
    }
    public Item(String name, String imageName, String tag){
        super(name, 0, 0, imageName, tag);

        visibility = false;
        isInInventory = true;

        deleteEntity(); //SE SUPPRIME
    }

    public void takeItem(){
        deleteEntity();
        Inventory.addItem(this);
    }

    public void dropItem(){

        visibility = true;
        isInInventory = false;
        coordX = ListEntity.getBoris().getCoordX();
        coordY = ListEntity.getBoris().getCoordY();
        
        move(coordX, coordY);
        Grid.setEntity(this);
        App.displaySprite(this);
    }

    public abstract void description();

    //Methode appellé lorsqu'on intéragi avec un item dans l'inventaire.
    public abstract void itemAction();
}
