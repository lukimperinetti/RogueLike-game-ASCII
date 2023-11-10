package com.roguelike_java;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * This class handles events related to user input, such as key presses and releases.
 * It is used in the App class.
 * @see App
 * Map is used to keep track of which keys are currently pressed.
 * @see Map
 * 
 */

public class EventHandler {

    private Map<KeyCode, Boolean> keyPressedMap = new HashMap<>();

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
    }

    public void handleKeyPressed(KeyCode keyCode) {
        switch (keyCode) {
            case Z:
                System.out.println("up");
                ListEntity.getBoris().relativeMove(0,-30);
                break;
            case Q:
                System.out.println("left");
                ListEntity.getBoris().relativeMove(-30,0);
                break;
            case S:
                System.out.println("down");
                ListEntity.getBoris().relativeMove(0,30);
                break;
            case D:
                System.out.println("right");
                ListEntity.getBoris().relativeMove(30,0);

                break;
            default:
                System.out.println("invalid key");
                break;
        }
    }
}
