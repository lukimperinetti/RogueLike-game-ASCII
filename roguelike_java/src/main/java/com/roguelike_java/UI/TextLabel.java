package com.roguelike_java.UI;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;


//Classe qui stock le label, son nombre de caractère et le nombre supposé de lignes qu'il est censé prendre.
public class TextLabel {
        
    public Label label;
    public int lines;
    public int sizeStr;
    public String text = "";
    private Font customFont;

    TextLabel(String text){

        sizeStr = text.length();

        lines = sizeStr/40; //Nombre de lignes

        customFont = Font.loadFont(getClass().getResource("/Fonts/Pokemon_Classic.ttf").toExternalForm(), 10); 


        for (int i = 0; i < lines; i++){
            this.text += text.substring(40*i, 40*(i+1)) + "\n";
        }
        this.text += text.substring(40*(lines), text.length());

        label = new Label(this.text);
        label.setFont(customFont);

    }
    TextLabel(String text, int X, int Y){
        sizeStr = text.length();

        lines = sizeStr/40; //Nombre de lignes

        customFont = Font.loadFont(getClass().getResource("/Fonts/Pokemon_Classic.ttf").toExternalForm(), 10); 


        for (int i = 0; i < lines; i++){
            this.text += text.substring(40*i, 40*(i+1)) + "\n";
        }
        this.text += text.substring(40*(lines), text.length());

        label = new Label(this.text);
        label.setFont(customFont);
    }

    public Label getLabel(){
        return label;
    }

}

