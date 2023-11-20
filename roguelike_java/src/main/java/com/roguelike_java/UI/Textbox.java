package com.roguelike_java.UI;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import com.roguelike_java.App;

import javafx.scene.control.Label;


//Classe de stockage des textbox.
public class Textbox {
    
    private ImageView sprite;
    private Label label;
    private String text;

    private int offsetX = 24;
    private int offsetY = 24;

    public Textbox(String text, int X, int Y, ImageView sprite){
        label = new Label(text);
        label.setTextFill(Color.WHITE);
        this.sprite = sprite;
        move(X, Y);
    }  

    //-----//
    //GETTER
    public ImageView getSprite(){
        return sprite;
    }

    public Label getLabel(){
        return label;
    }

    //METHODS 
    public void displayTextbox(){
        App.displaySprite(sprite);
        App.displayText(label);
    }
    public void deleteTextbox(){
        App.deleteSprite(sprite);
        App.deleteText(label);
    }
    public void setText(String text){
        label.setText(text);
    }

    public void move(int X, int Y){
        sprite.setTranslateX(X);
        sprite.setTranslateY(Y);

        label.setTranslateX(X+offsetX);
        label.setTranslateY(Y+offsetY);
    }
}
