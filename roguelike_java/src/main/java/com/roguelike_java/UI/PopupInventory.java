package com.roguelike_java.UI;

import com.roguelike_java.App;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

//Fenetre apparaissant a l'interaction d'un item dans l'inventaire.
public class PopupInventory {
    
    private static Image image = new Image("PopupInventory.png");
    private ImageView sprite;
    private static Image selector = new Image("selector-inventory.png");
    private ImageView selectorSprite;

    private ArrayList<Label> listLabel;

    private int popupSize;

    //Position :
    private int X;
    private int Y;

    //Option selectionne :
    private int selectedOption;
    private int lastSelectedOption;

    public PopupInventory(int X, int Y, ArrayList<String> listOption){
        popupSize = listOption.size();
        this.X = X;
        this.Y = Y;

        listLabel = new ArrayList<Label>();


        selectorSprite = new ImageView(selector);
        sprite = new ImageView(image);

        sprite.setScaleY(popupSize);
        sprite.setTranslateX(X);
        sprite.setTranslateY(Y + 8*popupSize);

        App.displaySprite(sprite);
        for (int i = 0; i < popupSize; i++){
            listLabel.add(new TextLabel(listOption.get(i)).getLabel());
            listLabel.get(i).setTranslateX(X);
            listLabel.get(i).setTranslateY(Y + (i*32));
            listLabel.get(i).setTextFill(Color.WHITE);
            App.displayText(listLabel.get(i));
        }
        App.displaySprite(selectorSprite);


        selectedOption = 0;
        lastSelectedOption = 0;

        updateSelector(0);
    }

    public void updateSelector(int k){

        if ( selectedOption + k >= 0 && selectedOption + k < popupSize){
            selectedOption += k;
        }
        if (selectedOption < listLabel.size()){
            selectorSprite.setTranslateX(X-1);
            selectorSprite.setTranslateY(Y + (selectedOption*32));
            listLabel.get(selectedOption).setTextFill(Color.BLACK);
            if (selectedOption != lastSelectedOption){
                listLabel.get(lastSelectedOption).setTextFill(Color.WHITE);
            }
        }

        lastSelectedOption = selectedOption;

        updatePopup();
    }


    //AFFICHAGE :
    public void deletePopup(){
        App.deleteSprite(sprite);
        App.deleteSprite(selectorSprite);

        for (Label label : listLabel) {
            App.deleteText(label);
        }
    }

    public void updatePopup(){
        sprite.toFront();
        
        for (Label label : listLabel) {
            label.toFront();
        }
        selectorSprite.toFront();
        listLabel.get(selectedOption).toFront();
    }

    public String getSelectedOption(){
        return listLabel.get(selectedOption).getText();
    }

}
