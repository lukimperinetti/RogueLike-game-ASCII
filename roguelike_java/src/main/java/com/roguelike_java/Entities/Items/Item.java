package com.roguelike_java.Entities.Items;

import com.roguelike_java.App;
import com.roguelike_java.Grid;
import com.roguelike_java.Inventory;
import com.roguelike_java.ListEntity;
import com.roguelike_java.Entities.Entity;
import java.util.ArrayList;

public abstract class Item extends Entity{
    
    protected boolean isInInventory;
    public ArrayList<String> listOption;
    protected String shortName; //Nom raccourci pour l'inventaire

    public Item(String name, int X, int Y, String imageName, String tag, String shortName){
        super(name, X, Y, imageName, tag);

        Grid.setEntity(this);

        visibility = false;
        isInInventory = false;

        listOption = new ArrayList<String>();
        listOption.add("Jeter");

        this.shortName = shortName;

        ListEntity.addItem(this);
    }
    public Item(String name, String imageName, String tag, String shortName){
        super(name, 0, 0, imageName, tag);

        visibility = false;
        isInInventory = true;

        listOption = new ArrayList<String>();
        listOption.add("Jeter");

        this.shortName = shortName;

        deleteEntity(); //SE SUPPRIME

        ListEntity.addItem(this);
    }

    //GETTERS :
    public String getShortName(){
        return shortName;
    }
    public void takeItem(){
        deleteEntity();
        Inventory.addItem(this);
    }
    public boolean isInInventory(){
        return isInInventory;
    }

    //SETTERS :
    public void setIsInInventory(boolean a){
        isInInventory = a;
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
    public void itemAction(String str){
        if (str.equals("Jeter")){

            Inventory.dropItem();

        }
    }

    public ArrayList<String> getListOption(){
        return listOption;
    }
}
