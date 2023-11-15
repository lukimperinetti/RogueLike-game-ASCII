package com.roguelike_java;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

import com.roguelike_java.Entities.Entity;

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
                ListEntity.getBoris().equipWeapon();
        }
    }
}
