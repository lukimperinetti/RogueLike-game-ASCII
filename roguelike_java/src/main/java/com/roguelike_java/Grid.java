package com.roguelike_java;

import java.util.ArrayList;

//Classe qui s'occupe de stocker les objets sur une grille, pour pouvoir facilement les retrouver et les afficher
public class Grid {

    private int sizeX;
    private int sizeY;
    private Entity Boris;

    private ArrayList<Entity> listEntity = new ArrayList<>(); // IDK ???
    public static ArrayList<ArrayList<ArrayList<Entity>>> grid = new ArrayList<ArrayList<ArrayList<Entity>>>(); // grille STATIC generale
    private Entity entityTest;

    public final static int sizeSprite = 16;

    Grid(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        // Initialisation de la 2d-list :
        for (int i = 0; i < sizeX; i++) {
            grid.add(new ArrayList<>());

            for (int j = 0; j < sizeY; j++) {
                grid.get(i).add(new ArrayList<>());

                // On instancie un "wall" dans chaque case
                entityTest = new Wall(i, j);
                entityTest.move(i, j);

                grid.get(i).get(j).add(entityTest);
                App.displaySprite(entityTest);
            }
        }

        // On instancie Boris
        Boris = new Boris(0, 0);
        App.displaySprite(Boris);

        // On crée une instance de EventHandler et lui donne la référence à l'entité
        // Boris pour qu'il soit moovable
        EventHandler eventHandler = new EventHandler(Boris);
        eventHandler.pollEvents(App.scene);
    }

    // GETTERS :
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    // Methode STATIC pour obtenir la grille generale du jeu
    public static ArrayList<ArrayList<ArrayList<Entity>>> getGrid() {
        return grid;
    }

}
