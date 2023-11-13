package com.roguelike_java;

import java.util.ArrayList;
import javafx.scene.control.Label;

//Classe qui stock le label, son nombre de caractère et le nombre supposé de lignes qu'il est censé prendre.
public class TextLabel {
        
    public Label label;
    public int lines;
    public int sizeStr;
    public String text = "";

    TextLabel(String text){

        sizeStr = text.length();

        lines = sizeStr/40; //Nombre de lignes

        for (int i = 0; i < lines; i++){
            this.text += text.substring(40*i, 40*(i+1)) + "\n";
        }
        this.text += text.substring(40*(lines), text.length()-1);
        System.out.println(this.text);

        label = new Label(this.text);

    }

}

