package com.roguelike_java;

import java.util.ArrayList;

import com.roguelike_java.Entities.Entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

//Classe qui s'occupe de faire apparaitre une popup a l'endroit ou la souris pointe.
//Classe principalement static. Pas sur de vouloir que ça le reste. (potentielles multiples popup ?)
//A REFACTORER, FONCTIONS PRINCIPALES PAS CLAIR.
public class PopupMouse {
    
    private static Image image = new Image("Bloc-test.png");
    private static ImageView popupSprite = new ImageView(image);

    private static int gridPositionX;
    private static int gridPositionY;
    private static int size = 0;
    private static int sizeMax = 3;

    private static boolean visibility = true; //pasutilisé

    //Popup :
    private static Image popupTop = new Image("top-popup.png");
    private static Image popupBot = new Image("bot-popup.png");
    private static Image popupMid = new Image("mid-popup.png");

    private static ImageView popupTopSprite = new ImageView(popupTop);
    private static ImageView popupBotSprite = new ImageView(popupBot);
    private static ArrayList<ImageView> listPopupMidSprite = new ArrayList<ImageView>();

    private static Label label;


    //GETTER 
    public static boolean isVisible(){
        return visibility;
    }

    public static void initPopup(){
        image = new Image("Bloc-test.png");
        popupSprite = new ImageView(image);


        App.displaySprite(popupSprite);

        for (int i = 0; i < 3; i++){
            listPopupMidSprite.add(new ImageView(popupMid));
        }
    }

    //A RENOMMER IMPERATIVEMENT.
    public static void movePopup(){

        gridPositionX = (EventHandler.getMouseX()/16);
        gridPositionY = (EventHandler.getMouseY()/16);

        //displayPopup("POUET");
        //popupSprite.setTranslateX(gridPositionX*16);
        //popupSprite.setTranslateY(gridPositionY*16);

        //popupSprite.toFront();
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

    //Fait apparaitre la popup
    public static void displayPopup(String message){

        int i = 1;
        size = 3;
        if (size > sizeMax){
            size = sizeMax;
        }

        makeInvisible();
        movePopup(); //Pour récupérer la position de la souris.

        //X
        popupTopSprite.setTranslateX((gridPositionX+1)*16);
        popupBotSprite.setTranslateX((gridPositionX+1)*16);

        for (i = 1; i <= size; i++){
            listPopupMidSprite.get(i-1).setTranslateX((gridPositionX+1)*16);
            listPopupMidSprite.get(i-1).setTranslateY((gridPositionY + (i*2))*16);
        }

        //Y
        popupTopSprite.setTranslateY(gridPositionY*16);
        popupBotSprite.setTranslateY((gridPositionY+(i*2))*16);

        makeVisible();

        //Tofront :
        popupTopSprite.toFront();
        popupBotSprite.toFront();  

    }

    //Methodes pour cacher ou montrer la popup
    public static void makeVisible(){
        if (visibility == false){
            visibility = true;
            App.displaySprite(popupTopSprite);
            App.displaySprite(popupBotSprite);

            for (int i = 0; i < size; i++){
                App.displaySprite(listPopupMidSprite.get(i));
            }
        }
    }
    public static void makeInvisible(){
        if (visibility == true){
            visibility = false;
            App.deleteSprite(popupTopSprite);
            App.deleteSprite(popupBotSprite);

            for (ImageView sprite : listPopupMidSprite) {
                System.out.println("pouet");
                App.deleteSprite(sprite);
            }
        }
    }



}
