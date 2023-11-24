package com.roguelike_java.Generators;

import com.roguelike_java.Utils;
import com.roguelike_java.Entities.Items.*;

//Permet de générer un item parmis une pool d'item
public class ItemGenerator {
    
    public static void itemGeneratorBasic(int X, int Y){

        double epee = 5;
        double pomme = 95;

        double random = (double) Utils.randint(0, 100);

        if (random < epee){
            new Sword(X, Y);
        }
        else {
            new Apple(X, Y);
        }
    }


}
