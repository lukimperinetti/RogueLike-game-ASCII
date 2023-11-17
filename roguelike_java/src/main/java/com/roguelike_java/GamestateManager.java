package com.roguelike_java;

import javafx.event.Event;
import javafx.scene.image.ImageView;

import com.roguelike_java.UI.*;


//S'occupe de stocker et rendre l'état actuel du jeu.
public class GamestateManager {
    
    static Textbox defeatTextbox;

    public static enum gameState {
        RUNNING,
        PAUSE,
        END;
    }

    private static gameState state;

    public static void initGamestate(){
        setGamestate(gameState.RUNNING);
    }

    public static gameState getGamestate(){
        return state;
    }

    //Change le gamestate. Lorsque le gamestat change, il le communique a l'eventHandler pour bloquer certaines entrée souris/clavier
    public static void setGamestate(gameState newState){
        state = newState;
        EventHandler.updateGamestate();
    }

    public static void defeat(){
        setGamestate(gameState.END);
        defeatTextbox = new Textbox("Vous etes mort.", 450, 200, new ImageView(ImageLoader.getTextbox4x8()));
        defeatTextbox.displayTextbox();
        
    }
}