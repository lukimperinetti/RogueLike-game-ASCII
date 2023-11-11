package com.roguelike_java;
import java.util.ArrayList;

//Class qui se charge de stocker tt les entity, pour facilement les retrouver.
public class ListEntity {
    

    private static Boris mainCaracter; //Contient l'instance du personnage joué

    private static ArrayList<Enemy> listEnemies = new ArrayList<Enemy>(); //Liste de tout les ennemis de la map.


    //Setters :
    public static void addEnemy(Enemy enemy){
        listEnemies.add(enemy);
    }

    public static void setBoris(Boris boris){
        mainCaracter = boris;
    }

    //Getters :
    public static Boris getBoris(){
        return mainCaracter;
    }

    public static ArrayList<Enemy> getListEnemies(){
        return listEnemies;
    }

}
