package com.roguelike_java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Entity {
    

    private int x;
    private int y;
    private Image spriteSheet;
    private ImageView sprite;

    //getters
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public ImageView getSprite() {
        return this.sprite;
    }

    public Entity (int x, int y, String spriteSheet) {
        this.x = x;
        this.y = y;
        this.spriteSheet = new Image(spriteSheet);
        this.sprite = new ImageView(this.spriteSheet); 
    }

}
