package com.roguelike_java;

import java.util.ArrayList;

import com.roguelike_java.Entities.*; //Flemme de voir quoi importer exactement.
import com.roguelike_java.Entities.Items.*;

//Classe qui s'occupe de stocker les objets sur une grille, pour pouvoir facilement les retrouver et les afficher
public class Grid {

    private static int sizeX;
    private static int sizeY;
    private Entity Boris;
    private Entity testEnemy;
    private Entity testEnemy2;
    private Entity testEnemy3;

    public static ArrayList<ArrayList<ArrayList<Entity>>> grid = new ArrayList<ArrayList<ArrayList<Entity>>>(); // grille
                                                                                                                // STATIC
                                                                                                                // generale

    private Entity entityTest;

    public final static int sizeSprite = 16;

    Grid(int sizeX, int sizeY) {
        grid.clear();
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        // Initialisation de la 2d-list :
        for (int i = 0; i < sizeX; i++) {
            grid.add(new ArrayList<>());

            for (int j = 0; j < sizeY; j++) {
                grid.get(i).add(new ArrayList<>());

                // On instancie un "wall" dans chaque case
                entityTest = new Wall(i, j);

                grid.get(i).get(j).add(entityTest);
            }
        }

        // GENERATION DE TERRAIN :
        DungeonGeneration.createRoom(0, 0, sizeX, sizeY); // salle principale
    }

    // GETTERS :
    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

    // GETTERS ALTERNATIFS :
    // Retourne le premier ennemi de la coordonnée donnée. Retourne null si aucun
    // ennemi
    public static Entity getEnnemy(int X, int Y) {
        ArrayList<Entity> listEntity = grid.get(X).get(Y);

        for (int i = 0; i < listEntity.size(); i++) {
            if (listEntity.get(i).getTag() == "enemy") {
                return listEntity.get(i);
            }
        }

        return null;
    }

    public static Weapon getWeapon(int X, int Y) {
        ArrayList<Entity> listEntity = getEntities(X, Y);

        for (int i = 0; i < listEntity.size(); i++) {
            if (listEntity.get(i).getTag() == "weapon") {
                return (Weapon) listEntity.get(i);
            }
        }

        return null;
    }

    // Renvoie toutes les entités d'une case donnée
    public static ArrayList<Entity> getEntities(int X, int Y) {
        return grid.get(X).get(Y);
    }

    // Methode STATIC pour obtenir la grille generale du jeu
    public static ArrayList<ArrayList<ArrayList<Entity>>> getGrid() {
        return grid;
    }

    // -------
    // METHODS
    // -------



    // Place un objet dans la grid a une position donnée
    public static void setEntity(Entity entity) {
        grid.get(entity.getCoordX()).get(entity.getCoordY()).add(entity);
    }

    public static void setEntity(Entity entity, int X, int Y) { // Arguments Alternatifs.
        grid.get(X).get(Y).add(entity);
    }

    // Supprime l'entity donnée de la grid.
    public static void deleteEntity(Entity entity) {
        grid.get(entity.getCoordX()).get(entity.getCoordY()).remove(entity);
    }





    //Cree un sol a la position spécifiée
    public static void createGround(int X, int Y){

        ArrayList<Entity> listEntity = Grid.getGrid().get(X).get(Y);

        if( listEntity.size() > 0){
            listEntity.get(0).deleteEntity();
        }
        listEntity.add(0, new Ground(X, Y));
    }

    public static void createStairs(int X, int Y){

         ArrayList<Entity> listEntity = Grid.getGrid().get(X).get(Y);

        if( listEntity.size() > 0){
            listEntity.get(0).deleteEntity();
        }
        listEntity.add(0, new Stairs(X, Y));
    }




    // Permet d'afficher un seul des entitées présentent sur une case donnée
    public static void displaySquareEntities(int X, int Y) {

        ArrayList<Entity> list = getEntities(X, Y);

        for (Entity entity : list) {
            App.deleteSprite(entity);
        }

        for (int i = 0; i < list.size(); i++) {

            if (list.get(list.size() - 1 - i).isVisible()) {
                App.displaySprite(list.get(list.size() - 1 - i));
                break;
            }

            i++;
        }
    }




    public static void newLevel(){
        //Desaffichage : 
        for (ArrayList<ArrayList<Entity>> listX : grid) {
            for (ArrayList<Entity> listY : listX) {
                for (Entity entity : listY) {
                    App.deleteSprite(entity);
                }
            }
        }
        ListEntity.clearLists();

        //Nouvelle grille :
        new Grid(App.sizeX, App.sizeY);
        ListEntity.getBoris().move(DungeonGeneration.getStartingPosX(), DungeonGeneration.getStartingPosY());
        ListEntity.getBoris().setVisibility(true);

    }

}
