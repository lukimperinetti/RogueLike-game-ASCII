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
    
    private static Image inventoryImage;
    private static ImageView inventorySprite;
    private static Image selector;
    private static ImageView selectorSprite;

    private static ArrayList<Item> inventory;
    private static ArrayList<Label> itemText;

    private static int offsetX = App.sizeX * Grid.sizeSprite;
    private static int offsetY = 0;

    private static int inventorySize = 10;
    private static int lineSize = 5;

    private static int selectedItem = 0;
    private static int lastSelectedItem = -1;

    //LOGIQUE// 
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

        //DEBUG :
        inventory.add(new Sword());
        updateInventory();
    }

    public static void addItem(Item item){

        inventory.add(item);
        updateInventory();

    }

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
    public static void displayInventory(){
        inventorySprite.toFront();
        selectorSprite.toFront();
        for (int i = 0; i < itemText.size(); i++){
            itemText.get(i).toFront();
        }
    }

    public static void displaySelector(){
        //inventorySprite.toFront();


        selectorSprite.setTranslateX(offsetX + 10 + (selectedItem/lineSize) * 100);
        selectorSprite.setTranslateY(offsetY + 15 + (selectedItem%lineSize) * 50);
        selectorSprite.toFront();


        itemText.get(selectedItem).setTextFill(Color.BLACK);
        if (lastSelectedItem >= 0 && lastSelectedItem != selectedItem){ itemText.get(lastSelectedItem).setTextFill(Color.WHITE); }

        itemText.get(selectedItem).toFront();
    }
}
