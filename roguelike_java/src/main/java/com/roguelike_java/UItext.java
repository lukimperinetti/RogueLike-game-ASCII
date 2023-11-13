package com.roguelike_java;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

//Classe pour afficher du texte in game a droite de l'écran de jeu.
public class UItext {
    
    private static ArrayList<Label> listLabel = new ArrayList<Label>();

    public static void printText(String text){

        Label label = new Label(text);
        listLabel.add(label);

        OffsetText(1);

        label.setTranslateX( (App.sizeX * Grid.sizeSprite) + 16);
        label.setTranslateY( (App.sizeY * Grid.sizeSprite) - 32);
        label.setTextFill(Color.WHITE);

        App.displayText(label);
    }

    //Fonction de décalage du texte
    public static void OffsetText(int lines){

        //For classique pour parcourir dans le sens inverse. On peut peut etre faire avec un foreach pour optimiser.
        for(int i = 0; i < listLabel.size(); i++){

            listLabel.get(listLabel.size() - 1 - i).setTranslateY((App.sizeY * Grid.sizeSprite) - 32 - ( i * 32 ));

        }
    }

}
