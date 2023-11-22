package com.roguelike_java.Generators;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.roguelike_java.Utils;
import com.roguelike_java.Entities.*;



//Permet de donner un ennemi parmis une pool d'ennemi
public class EnemyGenerator {
    

    private static ArrayList<Class<? extends Enemy>> listClassEnemy = new ArrayList<>();

    public void generateRandomEnemy(int X, int Y){

    }

    public static void test(int X, int Y){

        listClassEnemy.add(Goblin.class);
        listClassEnemy.add(Orc.class);

        try{
            
        Constructor<? extends Enemy> constructor = listClassEnemy.get(1).getDeclaredConstructor(int.class, int.class);
        Enemy enemyInstance = constructor.newInstance(X, Y);

        } catch (Exception e){ //Java oblige de track les Exceptions.
            e.printStackTrace();
        }
    }
    
}
