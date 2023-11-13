package com.roguelike_java;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

//Classe pour afficher du texte in game a droite de l'écran de jeu.
public class UItext {

    private static ArrayList<TextLabel> listLabel = new ArrayList<TextLabel>();

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

}
