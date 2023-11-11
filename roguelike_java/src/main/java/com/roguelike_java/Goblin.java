package com.roguelike_java;

public class Goblin extends Enemy{

    Goblin(int X, int Y){
        super("Goblin", X, Y, 50, 10);
        App.displaySprite(this);
    }

    public void doAction(){

        //SE DIRIGE VERS LE JOUEUR :
        int Xplayer = ListEntity.getBoris().coordX;
        int Yplayer = ListEntity.getBoris().coordY;

        int SignX = 1;
        int SignY = 1;

        if (Xplayer < coordX){ SignX = -1; }
        if (Yplayer < coordY){ SignY = -1;}

        if (Utils.distance1D(coordX, Xplayer) > Utils.distance1D(coordY, Yplayer)){ //Test de si le joueur est plus proche verticalement ou horizontalement
            relativeMove(SignX, 0);
        } else {
            relativeMove(0, SignY);
        }

    }

    @Override
    public Boolean canMove(int X, int Y){ 

        //CONDITION DE DEPLACEMENT
        for (int i = 0; i < Grid.getGrid().get(X).get(Y).size(); i ++){
                if(Grid.getGrid().get(X).get(Y).get(i).getName() == "Wall"){ return false; }
        }
        return true;
    }


}
