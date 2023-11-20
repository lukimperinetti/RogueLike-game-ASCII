package com.roguelike_java;

import javafx.scene.image.Image;

//Classe qui s'occupe de charger la plupart des images;
public class ImageLoader {
    
    private static Image textbox4x8;

    private static Image wall;
    private static Image ground;

    public static void LoadUIimage(){
        textbox4x8 = new Image("4x8_textbox.png");
    }

    public static void LoadEntities(){
        wall = new Image("Wall.png");
        ground = new Image("Ground.png");
    }

    //GETTER :
    public static Image getTextbox4x8(){
        return textbox4x8;
    }

    public static Image getWall(){
        return wall;
    }
    public static Image getGround(){
        return ground;
    }

}
