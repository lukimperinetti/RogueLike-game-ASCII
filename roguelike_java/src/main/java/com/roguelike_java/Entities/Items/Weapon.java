package com.roguelike_java.Entities.Items;

import com.roguelike_java.Inventory;
import com.roguelike_java.ListEntity;
import com.roguelike_java.Entities.Boris;

public abstract class Weapon extends Item{
    
    protected int atk;

    Weapon(String name, int X, int Y, String imageName, int atk, String shortName){
        super(name, X, Y, imageName, "weapon", shortName);

        this.atk = atk;

        listOption.add("Equiper");
    }
    Weapon(String name, String imageName, int atk, String shortName){
        super(name, imageName, "weapon", shortName);

        this.atk = atk;

        listOption.add("Equiper");
    }

    public int getAtk(){
        return atk;
    }

    //ACTION D'INVENTAIRE :
    @Override
    public void itemAction(String str){

        super.itemAction(str);

        if (str.equals("Equiper")){
            Inventory.removeItem(this);
            ListEntity.getBoris().equipWeapon(this);
        }
    }

}
