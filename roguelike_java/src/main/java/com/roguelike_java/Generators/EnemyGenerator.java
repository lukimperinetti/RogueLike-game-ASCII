package com.roguelike_java.Generators;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import java.lang.reflect.Field;
import com.roguelike_java.Utils;
import com.roguelike_java.Entities.*;



//Permet de générer un ennemi parmis une pool d'ennemi
public class EnemyGenerator {
    

    private static ArrayList<Class<? extends Enemy>> listClassEnemy = new ArrayList<>();

    public void generateRandomEnemy(int X, int Y){

    }

    public static void test(int X, int Y){
        //TEST AVEC REFLEXION
        listClassEnemy.add(Goblin.class);
        listClassEnemy.add(Orc.class);

        double sum = 0;
        Field poids;

        try{

        //Fait la somme des poids de proba de tout les monstres de la liste.
        for (int i = 0; i < listClassEnemy.size(); i++){

            poids = listClassEnemy.get(i).getField("poids");
            sum += (double)poids.get(null);
            System.out.println(sum);

        }
        poids = listClassEnemy.get(1).getField("poids");
        Constructor<? extends Enemy> constructor = listClassEnemy.get(1).getDeclaredConstructor(int.class, int.class);
        Enemy enemyInstance = constructor.newInstance(X, Y);


        } catch (Exception e){ //Java oblige de track les Exceptions.
            e.printStackTrace();
        }
    }

    public static void monsterGeneratorBasic(int X, int Y, int level){

        double sum = 0; // a modifier si je m'amuse a faire plus.

        double orc = 20;
        double goblin = 80;

        int random = Utils.randint(0, 100);

        if (random < orc){
            new Orc(X, Y);
        }
        else{
            new Goblin(X,Y);
        }

    }
    
}
