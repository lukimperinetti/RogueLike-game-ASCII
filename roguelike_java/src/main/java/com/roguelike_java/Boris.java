package com.roguelike_java;

public class Boris extends Unit {
    
    Boris(int X, int Y){
        super("Boris", X, Y, "Arobase.png", 20);
    }

    @Override
    public Boolean canMove(int X, int Y){ //Condition de déplacement du personnage joueur.
        for (int i = 0; i < Grid.getGrid().get(X).get(Y).size(); i ++){
                if(Grid.getGrid().get(X).get(Y).get(i).getName() == "Wall"){ return false; }
        }
        return true;
    }
}
