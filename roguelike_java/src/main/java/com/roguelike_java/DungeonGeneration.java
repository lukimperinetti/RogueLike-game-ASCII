package com.roguelike_java;

//Classe de génération d'environnnement : salles, couloirs, etc.
public class DungeonGeneration {
    

    public static void createRoom(int X, int Y, int sizeX, int sizeY){

        /*
        for( int i = 0; i < sizeX; i++){
            Grid.getGrid().get(X+i).get(Y).remove(0);
            Grid.getGrid().get(X+i).get(Y).add(new Wall(X+i, Y));
        }
        */

        for( int i = 1; i < sizeX-1; i++){
            for( int j = 1; j < sizeY-1; j++){

                Ground ground = new Ground(X+i, Y+j);
                //Suppression :
                App.deleteSprite(Grid.getGrid().get(X+i).get(Y+j).get(0)); //Gerer ça avec une vrai fonction.
                Grid.getGrid().get(X+i).get(Y+j).remove(0);

                //Creation :
                Grid.getGrid().get(X+i).get(Y+j).add(ground);
                App.displaySprite(ground);
            }
        }

    }
}
