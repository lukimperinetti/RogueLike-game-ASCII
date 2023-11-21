package com.roguelike_java.UI;

import java.util.ArrayList;

import com.roguelike_java.App;
import com.roguelike_java.Grid;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.text.Font;



//Classe qui affiche les PV.
//OPTI ATROCE
public class UILife {

    private static Label label;
    private static TextLabel textLabel;
    private static Image sprite;
    private static ArrayList<ImageView> listSpriteHP;
    private static Font customFont;

    private static int length = 128;


    public static void initLife(){
        sprite = new Image("HPbit.png");
        listSpriteHP = new ArrayList<ImageView>();

        textLabel = new TextLabel("HP : X/X", 0, 0);
        label = textLabel.getLabel();

        App.displayText(label);
        label.setTranslateY(App.sizeY*Grid.sizeSprite + 22);
        label.setTranslateX(20);
        label.setTextFill(Color.WHITE);

        for (int i = 0; i < length; i++){
            ImageView iView = new ImageView(sprite);
            listSpriteHP.add(iView);

            iView.setTranslateY(App.sizeY*Grid.sizeSprite + 24);
            iView.setTranslateX(120 + (2*i));

        }
    }

    public static void displayLife(int life, int maxlife){

        float normalizeLife = (float)life/maxlife;
        label.setText("HP : " + life + "/" + maxlife);

        hideLife();
        for(int i = 0; i < normalizeLife*length; i++){
            App.displaySprite(listSpriteHP.get(i));
        }
    }


    //Cache tout les sprites de PV.
    public static void hideLife(){
        for (ImageView spritView : listSpriteHP) {
            App.deleteSprite(spritView);
        }
    }
}
