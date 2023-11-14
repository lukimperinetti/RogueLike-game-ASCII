package com.roguelike_java;

import com.roguelike_java.Entities.*;

//METHODES STATICS
public class DungeonGeneration {

    // Création de la map
    public static void createRoom(int X, int Y, int sizeX, int sizeY) {
        int nbSalle = (int) (Math.random() * 7) + 4;

        // création d'un tableau 2D de booléens pour garder une trace des salles
        boolean[][] roomGrid = new boolean[Grid.getSizeX()][Grid.getSizeY()];

        // Création des salles aléatoires à l'intérieur de la map
        for (int k = 0; k < nbSalle; k++) {
            int roomX = (int) (Math.random() * 10) + 5; // entre 5 et 15
            int roomY = (int) (Math.random() * 10) + 5; // entre 5 et 15
            int posXRoom = (int) (Math.random() * (sizeX - roomX - 1)) + 1; // entre 1 et sizeX - roomX
            int posYRoom = (int) (Math.random() * (sizeY - roomY - 1)) + 1; // entre 1 et sizeY - roomY

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
                    Ground ground = new Ground(X + posXRoom + i, Y + posYRoom + j);
                    App.deleteSprite(Grid.getGrid().get(X + posXRoom + i).get(Y + posYRoom + j).get(0));
                    Grid.getGrid().get(X + posXRoom + i).get(Y + posYRoom + j).remove(0);
                    Grid.getGrid().get(X + posXRoom + i).get(Y + posYRoom + j).add(ground);

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
        }

        // Création des couloirs

    }


    // // Création de la grande salle :
    // for (int i = 1; i < sizeX - 1; i++) {
    // for (int j = 1; j < sizeY - 1; j++) {
    // Ground ground = new Ground(X + i, Y + j);
    // App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0));
    // Grid.getGrid().get(X + i).get(Y + j).remove(0);
    // Grid.getGrid().get(X + i).get(Y + j).add(ground);
    // }
    // }

    /*
     * Création d'une salle secondaire dans une salle principale
     */
    // public static void createBloc(int X, int Y, int sizeX, int sizeY) {
    // createRoom(X, Y, sizeX, sizeY);
    // for (int i = 0; i < sizeX; i++) {
    // for (int j = 0; j < sizeY; j++) {
    // if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1) {
    // Wall wall = new Wall(X + i, Y + j);
    // // Suppression :
    // App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0)); // Gerer ça
    // avec une vrai fonction.
    // Grid.getGrid().get(X + i).get(Y + j).remove(0);

    // // Creation :
    // Grid.getGrid().get(X + i).get(Y + j).add(wall);
    // // App.displaySprite(wall);
    // }
    // }
    // }
    // }

    // public static void connectVerticalBloc(int X, int Y, int sizeX, int sizeY) {
    // // Crée la salle
    // createRoom(X, Y, sizeX, sizeY);
    // // Connecte la salle en ajoutant des murs
    // for (int i = 0; i < sizeX; i++) {
    // for (int j = 0; j < sizeY; j++) {
    // // Ajoute un mur à toutes les positions sur les bords, excluant l'axe Y
    // if ((i == 0 || i == sizeX - 1) && (j != 0 && j != sizeY - 1)) {
    // // Crée un mur à la position actuelle
    // Wall wall = new Wall(X + i, Y + j);
    // // Suppression de l'élément existant
    // App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0));
    // Grid.getGrid().get(X + i).get(Y + j).remove(0);
    // // Ajoute le mur à la place
    // Grid.getGrid().get(X + i).get(Y + j).add(wall);
    // }
    // }
    // }
    // }

    // public static void connectHorizontalBloc(int X, int Y, int sizeX, int sizeY)
    // {
    // // Crée la salle
    // createRoom(X, Y, sizeX, sizeY);

    // // Connecte la salle en ajoutant des murs
    // for (int i = 0; i < sizeX; i++) {
    // for (int j = 0; j < sizeY; j++) {
    // // Ajoute un mur à toutes les positions sur les bords, excluant l'axe X
    // if ((j == 0 || j == sizeY - 1) && (i != 0 && i != sizeX - 1)) {
    // // Crée un mur à la position actuelle
    // Wall wall = new Wall(X + i, Y + j);

    // // Suppression de l'élément existant
    // App.deleteSprite(Grid.getGrid().get(X + i).get(Y + j).get(0));
    // Grid.getGrid().get(X + i).get(Y + j).remove(0);

    // // Ajoute le mur à la place
    // Grid.getGrid().get(X + i).get(Y + j).add(wall);
    // }
    // }
    // }
    // }

}
