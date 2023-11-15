package com.roguelike_java;

import java.util.ArrayList;

import com.roguelike_java.Entities.Entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Classe qui s'occupe de faire apparaitre une popup a l'endroit ou la souris pointe.
//Classe principalement static. Pas sur de vouloir que ça le reste. (potentielles multiples popup ?)
public class PopupMouse {
    
    private static Image image = new Image("Bloc-test.png");
    private static ImageView popupSprite = new ImageView(image);

    private static int gridPositionX;
    private static int gridPositionY;

    private static boolean visibility = true; //pasutilisé

    //GETTER 
    public boolean isVisible(){
        return visibility;
    }

    public static void initPopup(){
        image = new Image("Bloc-test.png");
        popupSprite = new ImageView(image);
        App.displaySprite(popupSprite);
    }

    public static void movePopup(){

        gridPositionX = (EventHandler.getMouseX()/16);
        gridPositionY = (EventHandler.getMouseY()/16);

        popupSprite.setTranslateX(gridPositionX*16);
        popupSprite.setTranslateY(gridPositionY*16);

        popupSprite.toFront();
    }

    public static void displayEntities(){        
        ArrayList<Entity> listEntity;
        
        if (gridPositionX < App.sizeX && gridPositionY < App.sizeY){
            listEntity = Grid.getEntities(gridPositionX, gridPositionY);

            System.out.println("---");
            for (Entity entity : listEntity) {
                System.out.println(entity.getName());
            }
        }
    } 

    //Methodes pour cacher ou montrer la popup
    public static void makeVisible(){
        if (visibility == false){
            visibility = true;
            App.displaySprite(popupSprite);
        }
        movePopup();
    }
    public static void makeInvisible(){
        if (visibility == true){
            visibility = false;
            App.deleteSprite(popupSprite);
        }
    }

}
