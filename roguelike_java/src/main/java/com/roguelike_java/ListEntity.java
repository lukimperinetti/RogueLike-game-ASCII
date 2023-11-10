package com.roguelike_java;
import java.util.ArrayList;

//Class qui se charge de stocker tt les entity, pour facilement les retrouver.
public class ListEntity {
    

    private static Entity mainCaracter;

    //Getters :
    public static void setBoris(Boris boris){
        mainCaracter = boris;
    }

    public static Entity getBoris(){
        return mainCaracter;
    }

}
