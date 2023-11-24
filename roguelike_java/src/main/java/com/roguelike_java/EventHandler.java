package com.roguelike_java;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

import com.roguelike_java.Entities.Entity;
import com.roguelike_java.UI.PopupMouse;
import com.roguelike_java.UI.UIequipment;

//Se charge de gérer les events généraux (entrées clavier et souris)
public class EventHandler {

    private Map<KeyCode, Boolean> keyPressedMap = new HashMap<>();

    private static int mouseX;
    private static int mouseY;

    private static GamestateManager.gameState state;
    private static Inventory.inventoryState inventoryState;

    //GETTER - SETTER 
    public static void updateGamestate(){
        state = GamestateManager.getGamestate();
    }
    public static void updateInventoryState(){
        inventoryState = Inventory.getState();
    }

    public void pollEvents(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (!keyPressedMap.getOrDefault(event.getCode(), false)) {
                handleKeyPressed(event.getCode());
                keyPressedMap.put(event.getCode(), true);
            }
        });

        scene.setOnKeyReleased(event -> {
            keyPressedMap.put(event.getCode(), false);
        });

        scene.setOnMouseMoved(event -> {
            handleMousePosition( (int) event.getSceneX() , (int) event.getSceneY() );
        });

        scene.setOnMouseClicked(event -> {
            handleMouseClick(event.getButton());

        });
    }
    
    //Entrée clavier :
    public void handleKeyPressed(KeyCode keyCode) {

        //System.out.println(keyCode);

        //MAIN GAME
        if(state == GamestateManager.gameState.RUNNING){
            switch (keyCode) {
                case Z:
                    ListEntity.getBoris().playerMove(0, -1);
                    break;
                case S:
                    ListEntity.getBoris().playerMove(0, 1);
                    break;
                case Q:
                    ListEntity.getBoris().playerMove(-1, 0);
                    break;
                case D:
                    ListEntity.getBoris().playerMove(1, 0);
                    break;
                case E:
                    ListEntity.getBoris().takeItem();
                    break;
                case F:
                    ListEntity.getBoris().interaction();
                    break;
                
            }
        }

        //INVENTAIRE :
        if (inventoryState == inventoryState.DEFAULT){
            switch (keyCode) {
                case UP:
                    Inventory.changeSelectedItem(-1, 0);
                    break;
                case DOWN:
                    Inventory.changeSelectedItem(1, 0);
                    break;
                case LEFT:
                    Inventory.changeSelectedItem(0, -1);
                    break;
                case RIGHT:
                    Inventory.changeSelectedItem(0, 1);
                    break;
                case ENTER:
                    //Inventory.dropItem();
                    Inventory.displayPopup();
                    break;

                case I:
                    UIequipment.displayEquipment();
                    break;
            }
        }
        else if (inventoryState == inventoryState.SELECTED){
            switch (keyCode) {
                case ESCAPE:
                    Inventory.setState(inventoryState.DEFAULT);
                    Inventory.deletePopup();
                    break;
                case UP:
                    Inventory.changeSelectedOption(-1);
                    break;
                case DOWN:
                    Inventory.changeSelectedOption(1);
                    break;
                case ENTER:
                    Inventory.useSelectedOption();
                    break;
            }
        }
        else if (inventoryState == inventoryState.HIDDEN){
            switch (keyCode) {
                case ESCAPE:
                    UIequipment.hideEquipment();
                    break;
            }
        }

        if (state == GamestateManager.gameState.END) {
            System.out.println("Tu dois quitter le jeu");
            if (keyCode == KeyCode.ESCAPE) {
                System.out.println("clique sur ESCAPE");
                // restart the game :
                GamestateManager.setGamestate(GamestateManager.gameState.MENU);
                // restart :
                
            }
        }
    }

    //Entrée souris :
    public void handleMousePosition(int mouseX, int mouseY){
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        //PopupMouse.movePopup();
    }

    public void handleMouseClick(MouseButton mouseButton){

        if(state == GamestateManager.gameState.RUNNING){
            if (mouseButton == MouseButton.PRIMARY) {
                PopupMouse.displayPopup("prout");
            }

            if (mouseButton == MouseButton.SECONDARY) {
                PopupMouse.makeInvisible();
            }
        }
    }


    //GETTERS
    public static int getMouseX(){
        return mouseX;
    }
    public static int getMouseY(){
        return mouseY;
    }
}
