package com.roguelike_java;

import java.util.ArrayList;

//Classe qui s'occupe de stocker les objets sur une grille, pour pouvoir facilement les retrouver et les afficher
public class Grid {
    
    private int sizeX;
    private int sizeY;

    private ArrayList<Entity> listEntity = new ArrayList<>(); //IDK ???
    private ArrayList<ArrayList<ArrayList<Entity>>> grid = new ArrayList<ArrayList<ArrayList<Entity>>>(); //Arraylist generale
    private Entity entityTest;

    Grid(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        //Test :
        entityTest = new Entity("pouet");
        listEntity.add(entityTest);

        System.out.println(grid);

        //Initialisation de la 2d-list :
        for (int i = 0; i < sizeX; i++){
            grid.add(new ArrayList<>());

            for (int j = 0; j < sizeY; j++){
                grid.get(i).add(new ArrayList<>());
            }
        }

        System.out.println(grid);

    }



    //GETTERS :
    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }
}
