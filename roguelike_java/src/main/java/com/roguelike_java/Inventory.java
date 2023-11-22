package com.roguelike_java;

import java.util.ArrayList;
import com.roguelike_java.Entities.*;
import com.roguelike_java.Entities.Items.*;
import com.roguelike_java.UI.*;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Classe qui se charge de stocker et afficher l'inventaire
public class Inventory {
    
    public static enum inventoryState{
        DEFAULT,
        SELECTED;
    }

    private static inventoryState state;

    private static Image inventoryImage;
    private static ImageView inventorySprite;
    private static Image selector;
    private static ImageView selectorSprite;

    private static PopupInventory actualPopup;

    private static ArrayList<Item> inventory;
    private static ArrayList<Label> itemText;

    private static int offsetX = App.sizeX * Grid.sizeSprite;
    private static int offsetY = 0;

    //Taille de l'inventaire et des lignes.
    private static int inventorySize = 10;
    private static int lineSize = 5;

    //Selection
    private static int selectedItem = 0;
    private static int lastSelectedItem = -1;

    //Selection de la popup
    private static int selectedOption = 0;


    //INITIALISATION : 
    public static void init(){
        //Background :
        inventoryImage = new Image("Inventory.png");
        inventorySprite = new ImageView(inventoryImage);
        selector = new Image("selector.png");
        selectorSprite = new ImageView(selector);

        //Background :
        inventorySprite.setTranslateX(offsetX);
        inventorySprite.setTranslateY(offsetY);
        App.displaySprite(inventorySprite);

        //Selector :
        selectorSprite.setTranslateX(offsetX);
        selectorSprite.setTranslateY(offsetY);
        App.displaySprite(selectorSprite);

        inventory = new ArrayList<Item>();
        itemText = new ArrayList<Label>();

        inventorySprite.toFront();

        for (int i = 0; i < inventorySize; i++){
            Label label = new TextLabel("--------").getLabel();
            itemText.add(label);

            label.setTranslateX(offsetX + 20 + (i/lineSize) * 100);
            label.setTranslateY(offsetY + 20 + (i%lineSize) * 50);

            label.setTextFill(Color.WHITE);
            App.displayText(label);
            label.toFront();

        }
        selectorSprite.toFront();
        displaySelector();

        //STATE :
        state = inventoryState.DEFAULT;
        EventHandler.updateInventoryState();

        //DEBUG :
        inventory.add(new Sword());
        updateInventory();
    }
    //-----------------------------------//
    //SETTER :
    public static void setState(inventoryState newState){
        state = newState;
        EventHandler.updateInventoryState();
    }

    //GETTER :
    public static inventoryState getState(){
        return state;
    }


    //METHODES :
    public static void addItem(Item item){

        inventory.add(item);
        updateInventory();

    }

    //Lache l'item sur le sol
    public static void dropItem(){
        if(inventory.size()-1 >= selectedItem){
            Item item = inventory.get(selectedItem);
            inventory.remove(item);
            item.dropItem();
            updateInventory();
        }

    }

    //Permet de changer l'item selectionné.
    public static void changeSelectedItem(int k, int j){
        lastSelectedItem = selectedItem;

        if (k!=0 && (selectedItem%lineSize)+k < lineSize && (selectedItem%lineSize)+k >= 0){
            selectedItem += k;
            displaySelector();
        }

        else if (( selectedItem + j*lineSize >= 0 && selectedItem + j*lineSize < inventorySize)){
            selectedItem += j*lineSize;
            displaySelector();
        }
    }

    //Update des labels de chaque item.
    public static void updateInventory(){

        for (Label label : itemText) {
            label.setText("------");
        }

        int i = 0;
        for (Item item : inventory) {
            itemText.get(i).setText(item.getName());
            i++;
        }
    }

    //AFFICHAGE//
    //Update l'ordre d'affichage de l'inventaire.
    public static void displayInventory(){
        inventorySprite.toFront();
        selectorSprite.toFront();
        for (int i = 0; i < itemText.size(); i++){
            itemText.get(i).toFront();
        }
    }

    //Selecteur indiquant quel item est selectionné.
    public static void displaySelector(){
        //inventorySprite.toFront();

        selectorSprite.setTranslateX(offsetX + 10 + (selectedItem/lineSize) * 100);
        selectorSprite.setTranslateY(offsetY + 15 + (selectedItem%lineSize) * 50);
        selectorSprite.toFront();


        itemText.get(selectedItem).setTextFill(Color.BLACK);
        if (lastSelectedItem >= 0 && lastSelectedItem != selectedItem){ itemText.get(lastSelectedItem).setTextFill(Color.WHITE); }

        itemText.get(selectedItem).toFront();
    }

    //GESTION DE LA POPUP :
    //Affiche la popup d'option quand on selectionne un item.
    public static void displayPopup(){

        setState(inventoryState.SELECTED);

        if (actualPopup != null) {
            actualPopup.deletePopup();
            displayInventory();
        }

        actualPopup = new PopupInventory(3, offsetX - 92 + (selectedItem/lineSize) * 100, offsetY + 15 + (selectedItem%lineSize) * 50);
    }

    public static void deletePopup(){
        if (actualPopup != null){
            actualPopup.deletePopup();
            displayInventory();
        }
    }

    public static void changeSelectedOption(int k){
        actualPopup.updateSelector(k);
    }


}
