package com.roguelike_java;

//Classe de génération d'environnnement : salles, couloirs, etc.
//METHODES STATICS
public class DungeonGeneration {

    public static void createRoom(int X, int Y, int sizeX, int sizeY) {

        /*
         * for( int i = 0; i < sizeX; i++){
         * Grid.getGrid().get(X+i).get(Y).remove(0);
         * Grid.getGrid().get(X+i).get(Y).add(new Wall(X+i, Y));
         * }
         */

        for (int i = 1; i < sizeX - 1; i++) {
            for (int j = 1; j < sizeY - 1; j++) {

                Ground ground = new Ground(X + i, Y + j);
                // Suppression :
                App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0)); // Gerer ça avec une vrai fonction.
                Grid.getGrid().get(X + i).get(Y + j).remove(0);

                // Creation :
                Grid.getGrid().get(X + i).get(Y + j).add(ground);
                // App.displaySprite(ground);
            }
        }

    }

    /*
     * Création d'une salle secondaire dans une salle principale
     */
    public static void createBloc(int X, int Y, int sizeX, int sizeY) {
        createRoom(X, Y, sizeX, sizeY);
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1) {
                    Wall wall = new Wall(X + i, Y + j);
                    // Suppression :
                    App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0)); // Gerer ça avec une vrai fonction.
                    Grid.getGrid().get(X + i).get(Y + j).remove(0);

                    // Creation :
                    Grid.getGrid().get(X + i).get(Y + j).add(wall);
                    // App.displaySprite(wall);
                }
            }
        }
    }

    public static void connectVerticalBloc(int X, int Y, int sizeX, int sizeY) {
        // Crée la salle
        createRoom(X, Y, sizeX, sizeY);
        // Connecte la salle en ajoutant des murs
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                // Ajoute un mur à toutes les positions sur les bords, excluant l'axe Y
                if ((i == 0 || i == sizeX - 1) && (j != 0 && j != sizeY - 1)) {
                    // Crée un mur à la position actuelle
                    Wall wall = new Wall(X + i, Y + j);
                    // Suppression de l'élément existant
                    App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0));
                    Grid.getGrid().get(X + i).get(Y + j).remove(0);
                    // Ajoute le mur à la place
                    Grid.getGrid().get(X + i).get(Y + j).add(wall);
                }
            }
        }
    }

    public static void connectHorizontalBloc(int X, int Y, int sizeX, int sizeY) {
        // Crée la salle
        createRoom(X, Y, sizeX, sizeY);
        
        // Connecte la salle en ajoutant des murs
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                // Ajoute un mur à toutes les positions sur les bords, excluant l'axe X
                if ((j == 0 || j == sizeY - 1) && (i != 0 && i != sizeX - 1)) {
                    // Crée un mur à la position actuelle
                    Wall wall = new Wall(X + i, Y + j);
                    
                    // Suppression de l'élément existant
                    App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0));
                    Grid.getGrid().get(X + i).get(Y + j).remove(0);
                    
                    // Ajoute le mur à la place
                    Grid.getGrid().get(X + i).get(Y + j).add(wall);
                }
            }
        }
    }
    
    
    
    
    
    
}