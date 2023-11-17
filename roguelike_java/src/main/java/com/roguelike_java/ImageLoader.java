package com.roguelike_java;

import javafx.scene.image.Image;

//Classe qui s'occupe de charger la plupart des images;
public class ImageLoader {
    
    private static Image textbox4x8;

    public static void LoadUIimage(){
        textbox4x8 = new Image("4x8_textbox.png");
    }

    //GETTER :
    public static Image getTextbox4x8(){
        return textbox4x8;
    }

}
