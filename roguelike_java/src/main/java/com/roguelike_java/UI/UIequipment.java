package com.roguelike_java.UI;

import com.roguelike_java.App;
import com.roguelike_java.Grid;
import com.roguelike_java.Inventory;
import com.roguelike_java.ListEntity;
import com.roguelike_java.Entities.Items.Weapon;
import com.roguelike_java.Inventory.inventoryState;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Affiche l'équipement actuel de Boris.
public class UIequipment {

    private static Image equipmentImage = new Image("Inventory.png");
    private static ImageView equipmentSprite;

    private static boolean isVisible = false;

    private static int offsetX = App.sizeX * Grid.sizeSprite;
    private static int offsetY = 0;

    private static Label title;
    private static Label weaponText;
    private static Label armorText;

    public static void init(){
        equipmentSprite = new ImageView(equipmentImage);
        equipmentSprite.setTranslateX(offsetX);
        equipmentSprite.setTranslateY(offsetY);

        //TITRE :
        title = new TextLabel("Inventaire : ").getLabel();
        title.setTranslateX(offsetX+20);
        title.setTranslateY(offsetY+5);
        title.setTextFill(Color.WHITE);

        //ARME :
        weaponText = new TextLabel("Arme : ").getLabel();
        weaponText.setTranslateX(offsetX + 30);
        weaponText.setTranslateY(offsetY + 35);
        weaponText.setTextFill(Color.WHITE);

        //ARMURE :
        armorText = new TextLabel("Armure : ").getLabel();
        armorText.setTranslateX(offsetX + 30);
        armorText.setTranslateY(offsetY + 95);
        armorText.setTextFill(Color.WHITE);

        updateEquipment();
    }

    public static void updateEquipment(){

        Weapon weapon = ListEntity.getBoris().getWeapon();
        if (weapon != null){
            weaponText.setText(
            "Arme : " + weapon.getName() + 
            "\nAttaque : " + weapon.getAtk());
        }
        else {
            weaponText.setText(
                "Arme : main nues"
                + "\n"
                + "Attaque : 10"
            );
        }



    }



    //Permet d'afficher par dessus l'inventaire l'equipement de Boris.
    public static void displayEquipment(){
        if (!isVisible){
            App.displaySprite(equipmentSprite);
            App.displayText(title);
            App.displayText(weaponText);
            App.displayText(armorText);

            Inventory.deletePopup();
        }
        Inventory.setState(inventoryState.HIDDEN);
        equipmentSprite.toFront();
        title.toFront();
        weaponText.toFront();
        armorText.toFront();

        isVisible = true;
    }
    //Pour cacher
    public static void hideEquipment(){
        if (isVisible){
            App.deleteSprite(equipmentSprite);
            App.deleteText(title);
            App.deleteText(weaponText);
            App.deleteText(armorText);

        }
        Inventory.setState(inventoryState.DEFAULT);
        Inventory.displayInventory();
        isVisible = false;
    }
}
