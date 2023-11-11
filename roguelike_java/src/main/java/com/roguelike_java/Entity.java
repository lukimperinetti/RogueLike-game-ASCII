package com.roguelike_java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Classe de base de tt les entités présentes in game
public class Entity {
    
    protected String name;
    protected int ID;
    protected static int globalID;

    protected int coordX;
    protected int coordY;

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

    // Visu :
    private Image image;
    public ImageView sprite;

    Entity(String name, int coordX, int coordY, String imageName){
        
        //ID :
        this.ID = globalID;
        globalID++;

        this.name = name;
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

        //Enregistre la nouvelle position
        this.coordX = X;
        this.coordY = Y;
    }

    public void relativeMove(int dX, int dY){ //deplacement relatif par rapport a la position actuelle.
        
        if(canMove(coordX+dX, coordY+dY)){
            Grid.getGrid().get(coordX).get(coordY).remove(this);

            //Met a jour les coordonnées du sprite :
            this.coordX += dX;
            this.coordY += dY;

            Grid.getGrid().get(coordX).get(coordY).add(this);

            //Deplace le sprite
            sprite.setTranslateX(coordX * Grid.sizeSprite);
            sprite.setTranslateY(coordY * Grid.sizeSprite);
        }
        else{ System.out.println("Deplacement impossible. "); }


    }

    public Boolean canMove(int X, int Y){ //A override si besoin.
        return true;
    }
}
