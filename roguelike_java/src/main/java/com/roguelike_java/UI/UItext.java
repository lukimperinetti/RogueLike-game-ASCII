package com.roguelike_java.UI;

import java.util.ArrayList;

import com.roguelike_java.App;
import com.roguelike_java.Grid;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Classe pour afficher du texte.
//S'occupe du récapitulatif en bas a droite.
//S'occupe aussi des différentes fenetres qui apparaissent
public class UItext {

    private static ArrayList<TextLabel> listLabel = new ArrayList<TextLabel>();
    private static ImageView spriteTextbox4x8;

    public static void printText(String text){

        TextLabel textLabel = new TextLabel(text);
        listLabel.add(textLabel);

        OffsetText();

        textLabel.label.setTranslateX( (App.sizeX * Grid.sizeSprite) + 16);
        textLabel.label.setTranslateY( (App.sizeY * Grid.sizeSprite) - 32 - (textLabel.lines * 16));
        textLabel.label.setTextFill(Color.WHITE);

        App.displayText(textLabel.label);

    }

    //Fonction de décalage du texte
    public static void OffsetText(){

        int offset = 0;
        //For classique pour parcourir dans le sens inverse. On peut peut etre faire avec un foreach pour optimiser.
        for(int i = 0; i < listLabel.size(); i++){

            TextLabel textLabel = listLabel.get(listLabel.size() - 1 - i);
            offset += textLabel.lines * 16;
            textLabel.label.setTranslateY((App.sizeY * Grid.sizeSprite) - 32 - ( i * 16 ) - (offset));

        }
    }

    public static void textBox4x8(String text){

    }

}
