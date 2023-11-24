package com.roguelike_java;

import com.roguelike_java.Entities.*;
import com.roguelike_java.Entities.Items.*;
import com.roguelike_java.Generators.EnemyGenerator;
import com.roguelike_java.Generators.ItemGenerator;

//METHODES STATICS
public class DungeonGeneration {

    private static int startingPosX = 0;
    private static int startingPosY = 0;



    //GETTERS :
    public static int getStartingPosX(){
        return startingPosX;
    }
    public static int getStartingPosY(){
        return startingPosY;
    }



    // Création de la map
    public static void createRoom(int X, int Y, int sizeX, int sizeY) {

        //Variables de stockage
        int oldRoomX = 0;
        int oldRoomY = 0;
        int oldSizeX = 0;
        int oldSizeY = 0;

        int roomX, roomY, posXRoom, posYRoom;
        roomX = 0;
        roomY = 0;
        posXRoom = 0;
        posYRoom = 0;

        int nbSalle = (int) (Math.random() * 7) + 4;

        // création d'un tableau 2D de booléens pour garder une trace des salles
        boolean[][] roomGrid = new boolean[Grid.getSizeX()][Grid.getSizeY()];

        // Création des salles aléatoires à l'intérieur de la map
        for (int k = 0; k < nbSalle; k++) {

            //Taille de la salle :
            roomX = (int) (Math.random() * 10) + 5;
            roomY = (int) (Math.random() * 10) + 5;
            //Position de la salle :
            posXRoom = (int) (Math.random() * (sizeX - roomX - 1)) + 1;
            posYRoom = (int) (Math.random() * (sizeY - roomY - 1)) + 1; 

 
            if (k == 0){
                startingPosX = posXRoom;
                startingPosY = posYRoom;
            }
            
            boolean areaFree = true;
            for (int i = -1; i <= roomX; i++) {
                for (int j = -1; j <= roomY; j++) {
                    if (X + posXRoom + i < 0 || X + posXRoom + i >= Grid.getSizeX() || Y + posYRoom + j < 0
                            || Y + posYRoom + j >= Grid.getSizeY()) {
                        continue;
                    }
                    if (roomGrid[X + posXRoom + i][Y + posYRoom + j]) {
                        areaFree = false;
                        break;
                    }
                }
                if (!areaFree)
                    break;
                    
            }

            // Si la zone n'est pas libre, on passe à l'itération suivante
            if (!areaFree)
                continue;

            for (int i = 0; i < roomX; i++) {
                for (int j = 0; j < roomY; j++) {

                    Grid.createGround(X + posXRoom + i, Y + posYRoom + j);

                    // Marque cette zone comme occupée dans la grille de la salle
                    roomGrid[X + posXRoom + i][Y + posYRoom + j] = true;
                }
            }

            // Ajoute une zone tampon autour de la salle pour éviter que les salles ne se touchent
            for (int i = -1; i <= roomX; i++) {
                for (int j = -1; j <= roomY; j++) {
                    if (X + posXRoom + i < 0 || X + posXRoom + i >= Grid.getSizeX() || Y + posYRoom + j < 0
                            || Y + posYRoom + j >= Grid.getSizeY()) {
                        continue;
                    }
                    roomGrid[X + posXRoom + i][Y + posYRoom + j] = true;
                }
            }

            if (k > 0){
                fillRoom(posXRoom, posYRoom, roomX, roomY);
                createLane(oldRoomX, oldRoomY, posXRoom, posYRoom);
            }

            oldRoomX = posXRoom;
            oldRoomY = posYRoom;
            oldSizeX = roomX;
            oldSizeY = roomY;

            
        }

        Grid.createStairs((int) ( Math.random()*oldSizeX ) + oldRoomX, (int) ( Math.random()*oldSizeY) + oldRoomY);
    }

    //Generation de couloir en 1 dimension
    public static void createLaneHorizontal(int X, int Y, int X2){

        int temp;
        if (X > X2) { temp = X2; X2 = X; X = temp; }

        for (int i = X; i <= X2; i++){
            Grid.createGround(i, Y);
        }
    }

    public static void createLaneVertical(int X, int Y, int Y2){

        int temp; 
        if (Y > Y2) { temp = Y2; Y2 = Y; Y = temp; }

        for (int i = Y2; i >= Y; i--){
            Grid.createGround(X, i);
        }
    }

    //Generation de couloir en 2 dimensions
    public static void createLane(int X1, int Y1, int X2, int Y2){

        int midpointX = Math.abs(X2-X1)/2 + Math.min(X1, X2);
        int midpointY = Math.abs(Y2-Y1)/2 + Math.min(Y1, Y2);

        createLaneHorizontal(X1, Y1, midpointX);
        createLaneHorizontal(midpointX, Y2, X2);
        createLaneVertical(midpointX, Y1, Y2);
    }


    //--------------------------//
    //GENERATION DANS LES SALLES :
    public static void fillRoom(int X, int Y, int sizeX, int sizeY){
        if (Math.random() > 0){
            monsterGeneration(X, Y, sizeX, sizeY);
        }
        if (Math.random() > 0.2){
            itemGeneration(X, Y, sizeX, sizeY);
        }
    }


    public static void monsterGeneration(int X, int Y, int sizeX, int sizeY){

        int ranX = (int)Math.random()*sizeX + X;
        int ranY = (int)Math.random()*sizeY + Y;

        for (int i = 1; i < Utils.randint(1, 4); i++){

            while (Grid.getEnnemy(ranX, ranY) != null){
                ranX = (int)Math.random()*sizeX + X;
                ranY = (int)Math.random()*sizeY + Y;
            }
            EnemyGenerator.monsterGeneratorBasic(ranX, ranY, 1);
        }
    }

    public static void itemGeneration(int X, int Y, int sizeX, int sizeY){

        int ranX = (int)Math.random()*sizeX + X;
        int ranY = (int)Math.random()*sizeY + Y;

        for (int i = 1; i < Utils.randint(1 , 3); i++){

            
            while (Grid.getItem(ranX, ranY) != null){
                ranX = (int) (Math.random()*sizeX + X);
                ranY = (int) (Math.random()*sizeY + Y);
                
            }

            ItemGenerator.itemGeneratorBasic(ranX, ranY);
        }
    }
}
