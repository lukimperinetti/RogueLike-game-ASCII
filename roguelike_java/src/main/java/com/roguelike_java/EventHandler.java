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
                playerEntity.move(playerEntity.getCoordX(), playerEntity.getCoordY() - 30); // 30 est la taille d'un sprite
                break;
            case S:
                playerEntity.move(playerEntity.getCoordX(), playerEntity.getCoordY() + 30);
                break;
            case Q:
                playerEntity.move(playerEntity.getCoordX() - 30, playerEntity.getCoordY());
                break;
            case D:
                playerEntity.move(playerEntity.getCoordX() + 30, playerEntity.getCoordY());
                break;
            default:
                System.out.println("invalid key");
                break;
        }
    }
}
