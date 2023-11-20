package com.roguelike_java.Entities;

import com.roguelike_java.App;
import com.roguelike_java.Grid;

public class Stairs extends Environment{
    
    public Stairs(int X, int Y){
        super("Stairs", X, Y, "stairs.png", "interactible");

    }
}
