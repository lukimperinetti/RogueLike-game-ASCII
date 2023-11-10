package com.roguelike_java;

public class Boris extends Entity {
    
    Boris(int X, int Y){
        super("Boris", X, Y, "Boris.png");
    }

    @Override
    public Boolean canMove(int X, int Y){
        for (int i = 0; i < Grid.getGrid().get(X).get(Y).size(); i ++){
                if(Grid.getGrid().get(X).get(Y).get(i).getName() == "Wall"){ return false; }
        }
        return true;
    }
}
