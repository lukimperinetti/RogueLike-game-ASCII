package com.roguelike_java;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class EventHandler {

    private Map<KeyCode, Boolean> keyPressedMap = new HashMap<>();
    private Entity playerEntity;  // Ajoute une référence à l'entité que tu veux déplacer

    public EventHandler(Entity playerEntity) {
        this.playerEntity = playerEntity;
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
    }

    public void handleKeyPressed(KeyCode keyCode) {
        switch (keyCode) {
            case Z:
                playerEntity.relativeMove(0, -1); // 30 est la taille d'un sprite
                break;
            case S:
                playerEntity.relativeMove(0, 1);
                break;
            case Q:
                playerEntity.relativeMove(-1, 0);
                break;
            case D:
                playerEntity.relativeMove(1, 0);
                break;
            default:
                System.out.println("invalid key");
                break;
        }
    }
}
