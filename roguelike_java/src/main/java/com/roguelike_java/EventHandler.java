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
                break;
            case Q:
                System.out.println("left");
                break;
            case S:
                System.out.println("down");
                break;
            case D:
                System.out.println("right");
                break;
            default:
                System.out.println("invalid key");
                break;
        }
    }
}
