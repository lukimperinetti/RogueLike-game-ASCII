package com.roguelike_java.Entities;

import com.roguelike_java.App;
import com.roguelike_java.Grid;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Classe de base de tt les entités présentes in game
public abstract class Entity {
    
    protected String name;
    protected String tag;
    protected int ID;
    protected static int globalID;

    protected int coordX;
    protected int coordY;

    protected Boolean visibility = false;

    private Image image;
    public ImageView sprite;

    //GETTERS :
    public int getCoordX(){
        return coordX;
    }
    public int getCoordY(){
        return coordY;
    }
    public String getName(){
        return name;
    }
    public String getTag(){
        return tag;
    }
    public Boolean isVisible(){
        return visibility;
    }


    public Entity(String name, int coordX, int coordY, String imageName, String tag){
        
        //ID :
        this.ID = globalID;
        globalID++;

        this.name = name;
        this.tag = tag;
        this.coordX = coordX;
        this.coordY = coordY;

        //Visu :
        image = new Image(imageName);
        sprite = new ImageView(image);   

        this.move(coordX, coordY);
    }

    public void move(int X, int Y){ //Fonction de déplacement absolu

        //Deplace le sprite
        sprite.setTranslateX(X * Grid.sizeSprite);
        sprite.setTranslateY(Y * Grid.sizeSprite);

        //Enregistre la nouvelle position + affichage
        //Grid.displaySquareEntities(coordX, coordY);
        this.coordX = X;
        this.coordY = Y;
        //Grid.displaySquareEntities(X, Y);
    }

    public void relativeMove(int dX, int dY){ //Deplacement relatif par rapport a la position actuelle.
        
        int oldX = coordX;
        int oldY = coordY;

        if(canMove(coordX+dX, coordY+dY)){
            Grid.deleteEntity(this);

            //Met a jour les coordonnées du sprite :
            this.coordX += dX;
            this.coordY += dY;

            Grid.setEntity(this);

            //Deplace le sprite
            sprite.setTranslateX(coordX * Grid.sizeSprite);
            sprite.setTranslateY(coordY * Grid.sizeSprite);

            //Affichage
            App.deleteSprite(this);
            Grid.displaySquareEntities(oldX, oldY);
            Grid.displaySquareEntities(coordX, coordY);
        }

    }

    //Défini ici pour pouvoir l'appeller a partir des entity obtenu dans la liste d'entity de la grid
    //Pas élégant. a voir si possible de faire mieux.
    public void loseHp(int damage){ 
        System.out.println("Fonction loseHp non défini.");
    }

    public void deleteEntity(){

        //A TESTER - DEBUG NON VERIF
        App.deleteSprite(this); //Se supprime de l'affichage.
        Grid.deleteEntity(this); //Se supprime de la grid.

    }

    public Boolean canMove(int X, int Y){ //A override si besoin.
        return true;
    }

    //Affiche ou désafiche un sprite.
    public void toggleVisibility(){

        visibility = !visibility;
        
        if(visibility){
            App.displaySprite(this);
        }
        else{
            App.deleteSprite(this);
        }
    }

}

